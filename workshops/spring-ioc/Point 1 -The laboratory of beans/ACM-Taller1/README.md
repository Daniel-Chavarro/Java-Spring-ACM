# 🧩 Creación, Selección y Posposición de Beans en Spring

## 📘 Descripción del problema
Este proyecto demuestra el uso de **@Component**, **@Bean**, **@Qualifier** y **@Lazy** en el contexto del contenedor IoC de Spring.  
El objetivo es crear varios Beans de distintos tipos, diferenciarlos por nombre y controlar el momento de su creación usando la anotación **@Lazy**.

---

## 🧱 Estructura del proyecto
- **ExperimentService:** Bean creado mediante `@Component`, representando una clase administrada automáticamente por Spring.
- **Config:** Clase de configuración con dos Beans definidos mediante `@Bean`, cada uno con un nombre propio para diferenciarlos.
- **SelectionService:** Clase que utiliza inyección de dependencias con `@Autowired` y `@Qualifier` para seleccionar cuál de los Beans definidos será utilizado.

---

## 💤 Implementación de @Lazy

### 🔹 Caso 1: `@Lazy` en `ExperimentService`
Cuando se añade la anotación `@Lazy` a `ExperimentService`, este Bean **no se crea al iniciar la aplicación**, ya que su instanciación se pospone hasta que sea solicitado explícitamente.

**Resultado esperado:**
- Se crean los Beans `Service1` y `Service2`.
- `ExperimentService` no se instancia porque no es requerido.

---

### 🔹 Caso 2: `@Lazy` en los Beans de `Config`
Al aplicar `@Lazy` a los Beans definidos dentro de la clase `Config`, estos **solo se instancian cuando son realmente utilizados**.

**Resultado esperado:**
- Solo se crea el Bean solicitado (`Service1` en este caso).
- El resto de Beans definidos permanecen sin instanciar hasta que sean requeridos.

---

## 🧠 Conclusiones
- Los Beans definidos con **@Bean** requieren una clase de configuración y un método que los genere.
- Los Beans creados con **@Component** se gestionan automáticamente con solo usar la anotación.
- El comportamiento de ejecución es equivalente en ambos tipos de Beans.
- La anotación **@Lazy** permite optimizar la carga de recursos al **posponer la creación de Beans hasta su uso real**.

---
