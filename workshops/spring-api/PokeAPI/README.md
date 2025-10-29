# PokeAPI - Spring Boot WebFlux

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Project-blue)
![WebFlux](https://img.shields.io/badge/WebFlux-Reactive-purple)

API REST reactiva construida con Spring Boot WebFlux que consume [PokeAPI](https://pokeapi.co/) para obtener información sobre Pokémon y sus habilidades.

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#️-tecnologías)
- [Arquitectura](#-arquitectura)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Uso](#-uso)
- [Endpoints](#-endpoints)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Modelos de Datos](#-modelos-de-datos)
- [Manejo de Errores](#-manejo-de-errores)
- [Conceptos Reactivos](#-conceptos-reactivos)

## ✨ Características

- **Programación Reactiva**: Utiliza Project Reactor (Mono y Flux) para operaciones no bloqueantes
- **WebClient**: Consumo de API externa de forma asíncrona
- **Composición de Datos**: Enriquece la información de Pokémon con detalles de habilidades en inglés
- **Manejo de Errores**: Respuestas HTTP apropiadas (404) para recursos no encontrados
- **Arquitectura en Capas**: Separación clara entre Controllers, Services y Models

## 🛠️ Tecnologías

- **Java 25**
- **Spring Boot 3.5.7**
- **Spring WebFlux** - Framework reactivo
- **Project Reactor** - Librería de programación reactiva
- **WebClient** - Cliente HTTP no bloqueante
- **Jackson** - Procesamiento de JSON
- **Maven** - Gestión de dependencias

## 🏗 Arquitectura

El proyecto sigue una arquitectura en capas:

```
┌─────────────────┐
│   Controller    │ ← Capa de presentación (REST endpoints)
└────────┬────────┘
         │
┌────────▼────────┐
│    Service      │ ← Lógica de negocio y composición reactiva
└────────┬────────┘
         │
┌────────▼────────┐
│   WebClient     │ ← Cliente HTTP para consumir PokeAPI
└─────────────────┘
```

## 📦 Requisitos Previos

- **Java JDK 25** o superior
- **Maven 3.6+**
- Conexión a Internet (para consumir PokeAPI)

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd PokeAPI
```

### 2. Compilar el proyecto

**Windows (cmd.exe):**
```cmd
mvnw.cmd clean install
```

**Linux/Mac:**
```bash
./mvnw clean install
```

### 3. Ejecutar la aplicación

**Windows:**
```cmd
mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## 💡 Uso

### Obtener un Pokémon por nombre

```bash
curl "http://localhost:8080/pokeapi/pokemon?name=pikachu"
```

**Respuesta (200 OK):**
```json
{
  "id": 25,
  "name": "pikachu",
  "weight": 60,
  "abilities": [
    {
      "id": 9,
      "name": "static",
      "effect": "Has a 30% chance of paralyzing attacking Pokémon on contact."
    },
    {
      "id": 31,
      "name": "lightning-rod",
      "effect": "Redirects single-target electric moves to this Pokémon where possible. Absorbs Electric moves, raising Special Attack one stage."
    }
  ]
}
```

### Pokémon no encontrado

```bash
curl "http://localhost:8080/pokeapi/pokemon?name=pokemonfalso"
```

**Respuesta (404 Not Found):**
```json
{
  "timestamp": "2025-10-29T10:30:00",
  "status": 404,
  "error": "Not Found",
}
```

## 📡 Endpoints

### Pokémon

| Método | Endpoint | Parámetros | Descripción |
|--------|----------|------------|-------------|
| GET | `/pokeapi/pokemon` | `name` (query param) | Obtiene un Pokémon con sus habilidades |

**Ejemplo:**
- `/pokeapi/pokemon?name=bulbasaur`
- `/pokeapi/pokemon?name=charizard`
- `/pokeapi/pokemon?name=mewtwo`

## 📂 Estructura del Proyecto

```
src/main/java/org/apis/pokeapi/
├── PokeApiApplication.java          # Clase principal de Spring Boot
├── config/
│   └── ConfigWebClient.java         # Configuración de WebClient
├── controller/
│   └── PokemonController.java       # Endpoints REST
├── service/
│   ├── PokemonService.java          # Lógica para obtener Pokémon
│   └── AbilityService.java          # Lógica para obtener habilidades
├── model/
│   ├── Pokemon.java                 # Modelo de Pokémon
│   └── Ability.java                 # Modelo de habilidad
└── exception/
    ├── PokemonNotFoundException.java   # Excepción para Pokémon no encontrado
    └── AbilityNotFoundException.java   # Excepción para habilidad no encontrada
```

## 📊 Modelos de Datos

### Pokemon

```java
{
  "id": 25,                    // ID único del Pokémon
  "name": "pikachu",           // Nombre del Pokémon
  "weight": 60,                // Peso (en hectogramos)
  "abilities": [...]           // Lista de habilidades
}
```

### Ability

```java
{
  "id": 9,                     // ID único de la habilidad
  "name": "static",            // Nombre de la habilidad
  "effect": "Has a 30%..."     // Descripción corta en inglés
}
```

## ⚠️ Manejo de Errores

El proyecto implementa manejo de errores mediante:

### 1. Anotación `@ResponseStatus`

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PokemonNotFoundException extends RuntimeException {
    // Automáticamente retorna 404
}
```

### 2. Interceptor de WebClient

```java
.onStatus(
    status -> status.value() == 404,
    resp -> Mono.error(new PokemonNotFoundException("Pokemon no encontrado: " + name))
)
```

### Códigos de estado HTTP

| Código | Descripción |
|--------|-------------|
| 200 | Recurso encontrado exitosamente |
| 400 | Nombre de Pokémon inválido (vacío, null o solo espacios) |
| 404 | Pokémon o habilidad no encontrado |
| 500 | Error interno del servidor |

### Validación de entrada

El servicio implementa validación de preflight que:
- Rechaza nombres `null`, vacíos o con solo espacios en blanco (retorna 400)
- Normaliza nombres válidos con `trim()` y `toLowerCase()` antes de consultar la API
- Previene llamadas a `/pokemon/` con identificadores vacíos

**Ejemplo de error 400:**
```bash
curl "http://localhost:8080/pokeapi/pokemon?name=%20%20%20"
```

**Respuesta (400 Bad Request):**
```json
{
  "timestamp": "2025-10-29T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Pokemon name cannot be null, empty, or whitespace-only"
}
```


## 🔧 Configuración

### WebClient

Configurado para apuntar a la API base de PokeAPI:

```java
@Bean
public WebClient webClient() {
    return WebClient.builder()
        .baseUrl("https://pokeapi.co/api/v2")
        .build();
}
```

### Application Properties

```properties
spring.application.name=PokeAPI
```


## 📝 Notas Adicionales

### Filtrado de Idioma

El proyecto filtra automáticamente las descripciones de habilidades para obtener solo la versión en inglés:

```java
private String getShortEffect(JsonNode node) {
    return StreamSupport.stream(entries.spliterator(), false)
        .filter(entry -> "en".equals(entry.path("language").path("name").asText(null)))
        .map(entry -> entry.path("short_effect").asText(null))
        .findFirst()
        .orElse(null);
}
```

### Inyección de Dependencias

El proyecto usa **inyección por constructor** (recomendada por Spring):

```java
@Autowired
public PokemonService(WebClient webClient, AbilityService abilityService) {
    this.webClient = webClient;
    this.abilityService = abilityService;
}
```

## 🤝 Contribuciones

Este es un proyecto educativo para el workshop de Spring ACM. Sugerencias y mejoras son bienvenidas.

## 📄 Licencia

Proyecto educativo - ACM Java Spring Workshop

## 🔗 Referencias

- [PokeAPI Documentation](https://pokeapi.co/docs/v2)
- [Spring WebFlux](https://docs.spring.io/spring-framework/reference/web/webflux.html)
- [Project Reactor](https://projectreactor.io/docs/core/release/reference/)
- [WebClient](https://docs.spring.io/spring-framework/reference/web/webflux-webclient.html)

---

**Desarrollado con ❤️ para el workshop de Spring Boot + WebFlux**


