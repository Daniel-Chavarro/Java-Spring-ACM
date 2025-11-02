# WeatherForecast - Spring Boot WebFlux

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Project-blue)
![WebFlux](https://img.shields.io/badge/WebFlux-Reactive-purple)

API REST reactiva construida con Spring Boot WebFlux que consume la API de OpenWeatherMap para obtener y procesar información meteorológica con resúmenes para las próximas 24 horas y 3 días.

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#️-tecnologías)
- [Arquitectura](#-arquitectura)
- [Requisitos Previos](#-requisitos-previos)
- [Uso](#-uso)
- [Estructura del Proyecto](#-estructura-del-proyecto)

## ✨ Características

- **Programación Reactiva**: Manejo de flujo de datos con Reactor (Mono)
- **WebClient**:Cliente HTTP reactivo para consumo de API externa
- **Resumen Meteorologico**:Temperatura promedio, descripción y última actualización para 24 horas y 3 días
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
│   WebClient     │ ← Cliente HTTP para consumir OpenWeatherMap
└─────────────────┘
```

## 📦 Requisitos Previos

- **Java JDK 25** o superior
- **Maven 3.6+**
- Conexión a Internet para consumir OpenWeatherMap API

La aplicación estará disponible en `http://localhost:8080`

## 💡 Uso

### Obtener resumen clima proximas 24h

```bash
curl "http://localhost:8080/api/forecast/summary/24h?city=CiudadEjemplo"

```
*Respuesta(city=Bogota)
```json
{
  "averageTemperature": 14.09,
  "generalDescription": "light rain",
  "lastUpdateTime": "2025-11-03 15:00:00"
}
```
### Obtener resumen del clima para los proximos 3 dias
```bash
curl "http://localhost:8080/api/forecast/summary/3days?city=CiudadEjemplo"
```
**Respuesta (city=Bogota):**
```json
{
  "averageTemperature": 13.65875,
  "generalDescription": "light rain",
  "lastUpdateTime": "2025-11-05 15:00:00"
}
```
## 📂 Estructura del Proyecto

```
src/main/java/talleres.punto1
├── config/
│   └── ConfigWebClient.java            # Configuración WebClient
├── controller/
│   └── ForecastController.java         # Endpoints REST
├── service/
│   └── ForecastService.java            # Lógica y consumo API externa
├── model/
│   └── ForecastResponse.java           # Modelos para mapear JSON de OpenWeatherMap
├── Punto1Application.java         # Clase principal Spring Boot

```


### Application Properties

```properties
spring.application.name=Punto1
```



## 🤝 Contribuciones

Este es un proyecto educativo para el workshop de Spring ACM. Sugerencias y mejoras son bienvenidas.

## 📄 Licencia

Proyecto educativo - ACM Java Spring Workshop

## 🔗 Referencias

- [Weather Forecast documentation](https://openweathermap.org/forecast5)
- [Spring WebFlux](https://docs.spring.io/spring-framework/reference/web/webflux.html)
- [Project Reactor](https://projectreactor.io/docs/core/release/reference/)
- [WebClient](https://docs.spring.io/spring-framework/reference/web/webflux-webclient.html)

---



