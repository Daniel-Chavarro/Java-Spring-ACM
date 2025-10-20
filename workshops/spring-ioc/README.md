# 🧩 Punto 1 - Creación, Selección y Posposición de Beans en Spring

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
# 🧩 Punto 3 — La Conspiración de los *Qualifiers*

## 🎯 Tema
**Resolución de dependencias y ambigüedades en el contenedor de Spring.**

## 🧠 Descripción del problema
En este ejercicio se analizó el comportamiento del contenedor de **Spring** al inyectar dependencias cuando existen múltiples implementaciones de una misma interfaz.

Se definió la interfaz `DiscountService` y dos clases que la implementan:  
`BasicDiscountService` y `PremiumDiscountService`, ambas anotadas con `@Service`.

Posteriormente, en la clase `OrderService` se intentó inyectar un objeto de tipo `DiscountService` sin especificar cuál implementación debía usarse.  
El contexto de Spring lanzó una excepción durante la inicialización.

## 💻 Código base
```java
public interface DiscountService {
    double applyDiscount(double amount);
}

@Service
public class BasicDiscountService implements DiscountService {
    public double applyDiscount(double amount) {
        return amount * 95 / 100;
    }
}

@Service
public class PremiumDiscountService implements DiscountService {
    public double applyDiscount(double amount) {
        return amount * 90 / 100;
    }
}

@Service
public class OrderService {
    private final DiscountService discountService;

    @Autowired
    public OrderService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void processOrder(double amount) {
        double finalPrice = discountService.applyDiscount(amount);
        System.out.println("Final amount after discount: " + finalPrice);
    }
}
```

## ⚠️ Error obtenido
Al ejecutar la clase `Runner` que inicializa el contexto con `AnnotationConfigApplicationContext`, se produjo el siguiente error:

```text
org.springframework.beans.factory.UnsatisfiedDependencyException:
No qualifying bean of type 'DiscountService' available:
expected single matching bean but found 2:
basicDiscountService, premiumDiscountService
```

Este error ocurre porque Spring encuentra **dos beans del mismo tipo** (`DiscountService`) y no puede determinar automáticamente cuál debe inyectar.

---

## 🛠️ Solución implementada

Existen dos formas comunes de resolver esta ambigüedad:

### ✅ 1. Usar la anotación `@Primary`
```java
@Primary
@Service
public class BasicDiscountService implements DiscountService { ... }
```

De esta forma, Spring utilizará siempre la implementación marcada como **primaria** cuando no se especifique un `@Qualifier`.

**🧾 Resultado en consola:**
```text
Enter the order amount:
1000000
Final amount after discount: 950000.0
```

---

### 🎯 2. Especificar explícitamente el bean con `@Qualifier`
```java
@Service
public class OrderService {
    private final DiscountService discountService;

    @Autowired
    public OrderService(@Qualifier("premiumDiscountService") DiscountService ds) {
        this.discountService = ds;
    }
}
```
El nombre del bean (`"premiumDiscountService"`) coincide con el nombre de la clase con la primera letra en minúscula.

**🧾 Resultado en consola:**
```text
Enter the order amount:
1000000
Final amount after discount: 900000.0
```

---

### 🧩 Casos especiales con nombres de beans

#### 🚫 Caso: Nombres Incorrectos
```java
@Service("premiumDiscountApplyService")
class PremiumDiscountService implements DiscountService {
```
Resultado:
```text
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: 
No qualifying bean of type 'org.qualifiers.service.discount.DiscountService' available: 
expected at least 1 bean which qualifies as autowire candidate. 
Dependency annotations: {@org.springframework.beans.factory.annotation.Qualifier("premiumDiscountService")}
```
➡️ Indica que el bean especificado en `@Qualifier` **no existe**.

#### ⚔️ Caso: Nombres Iguales
```java
@Service("discountService")
class BasicDiscountService implements DiscountService { ... }

@Service("discountService")
class PremiumDiscountService implements DiscountService { ... }

@Autowired
public OrderService(@Qualifier("discountService") DiscountService discountService) {
    this.discountService = discountService;
}
```
Resultado:
```text
Annotation-specified bean name 'discountService' for bean class 
[org.qualifiers.service.discount.PremiumDiscountService] conflicts with existing, 
non-compatible bean definition of same name and class 
[org.qualifiers.service.discount.BasicDiscountService]
```

---

## 🧩 Uso de `@Autowired(required = false)`
Por defecto, `@Autowired` **requiere** que el bean exista en el contexto; de lo contrario, el contenedor lanza una excepción `NoSuchBeanDefinitionException`.

Spring permite modificar este comportamiento marcando la dependencia como **opcional** mediante el atributo `required = false`:

```java
@Service
public class OrderService {
    private final DiscountService discountService;

    @Autowired(required = false)
    public OrderService(@Qualifier("experimentalDiscountService") DiscountService discountService) {
        this.discountService = discountService;
    }

    public OrderService() {
        this.discountService = null;
    }

    public void placeOrder(double amount) {
        if (discountService != null) {
            double discountedAmount = discountService.applyDiscount(amount);
            System.out.println("Order placed. Original amount: " + amount + 
                               ", Discounted amount: " + discountedAmount);
        } else {
            System.out.println("Order placed. Amount: " + amount + 
                               " (No discount applied)");
        }
    }
}
```

**🧾 Resultado en consola:**
```text
Enter the order amount:
1000000
Order placed. Amount: 1000000.0 (No discount applied)
```

🧠 Cuando se usa `@Autowired(required = false)` sobre un constructor, si el bean no existe, se requiere un **constructor alternativo** que no utilice esa dependencia.  
Esto también aplica para la inyección mediante *setters*.


### 🧩 Conclusión

El uso de `@Primary` simplifica la configuración cuando se desea una implementación por defecto, mientras que `@Qualifier` brinda control preciso al seleccionar entre varias implementaciones.

Comprender esta diferencia es fundamental para evitar errores de inyección en aplicaciones con múltiples estrategias o servicios similares.

---

# 🔁 Punto 4 — El Bucle Infinito

## 🎯 Tema
**Dependencias circulares, orden de inicialización y contexto de carga en Spring.**

## 🧠 Descripción del problema
En este ejercicio se explora el comportamiento del contenedor de **Spring** cuando dos beans se necesitan mutuamente durante la inicialización.  
El caso presentado ocurre entre las clases `InventoryService` y `OrderService`, las cuales se inyectan entre sí mediante sus constructores.  
Al iniciar la aplicación, el contenedor intenta crear ambos beans simultáneamente, provocando una **dependencia circular imposible de resolver.**

---

## 💻 Código base

### 📦 `InventoryService`
```java
package org.loop.withLoops.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final OrderService orderService;

    @Autowired
    public InventoryService(OrderService orderService) {
        System.out.println("Starting Inventory Service");
        this.orderService = orderService;
    }

    public boolean checkInventory(String product) {
        System.out.println("Checking if Inventory exists for product " + product);
        return true;
    }

    public void verifyIfOrderExits(String product) {
        if (orderService.verifyOrder(product)) {
            System.out.println("Order has been executed for product " + product);
        } else  {
            System.out.println("Order has been cancelled for product " + product);
        }
    }
}
```

### 📦 `OrderService`
```java
package org.loop.withLoops.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final InventoryService inventoryService;

    @Autowired
    public OrderService(InventoryService inventoryService) {
        System.out.println("Starting Order Service");
        this.inventoryService = inventoryService;
    }

    public void createOrder(String product) {
        if (inventoryService.checkInventory(product)) {
            System.out.println("Order has been created");
        } else  {
            System.out.println("Order has not been created, out of stock");
        }
    }

    public boolean verifyOrder(String product) {
        System.out.println("Checking if order exists for product " + product);
        return true;
    }
}
```

### 🚀 `Runner`
```java
package org.loop.withLoops;

import org.loop.withLoops.service.InventoryService;
import org.loop.withLoops.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan
public class Runner {

    static void main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);

        Scanner scanner = context.getBean(Scanner.class);
        OrderService orderService = context.getBean(OrderService.class);
        InventoryService inventoryService = context.getBean(InventoryService.class);

        System.out.println("Enter product name:");
        String productName = scanner.next();

        orderService.createOrder(productName);
        inventoryService.verifyIfOrderExits(productName);
    }
}
```

---

## ⚠️ Error obtenido
Durante la ejecución del programa, Spring no logra crear el contexto debido a la referencia circular entre los servicios.

```console
Error creating bean with name 'orderService': Unsatisfied 
dependency expressed through constructor parameter 0: Error 
creating bean with name 'inventoryService': Requested bean 
is currently in creation: Is there an unresolvable circular 
reference or an asynchronous initialization dependency?
```

➡️ Este error indica que el contenedor intenta crear el bean `OrderService`, pero para hacerlo necesita un `InventoryService` ya inicializado,
el cual a su vez depende nuevamente de `OrderService`, generando así un **bucle infinito de inicialización.**

---

## 🔍 Análisis
La excepción `BeanCurrentlyInCreationException` aparece cuando **dos o más beans dependen entre sí por constructor**.  
El flujo del error puede describirse así:

1. 🌀 Spring detecta `OrderService` y crea una instancia, pero antes de completarla intenta inyectar `InventoryService`.
2. ⚙️ El contenedor procede a crear `InventoryService`, pero este requiere nuevamente un `OrderService`.
3. ❌ Como `OrderService` aún no está completamente inicializado, el ciclo no puede romperse y Spring lanza la excepción.

---

## 🛠️ Solución aplicada
Para resolver este comportamiento se utilizó una de las estrategias recomendadas en Spring: **romper la dependencia directa.**  
En este ejercicio, la corrección se realizó aplicando `@Lazy` en una de las dependencias o usando inyección por *setter*, lo que permite que el contenedor posponga la creación del bean hasta que realmente se necesite.

```java
@Lazy
@Autowired
public InventoryService(OrderService orderService) { ... }
```

**🧾 Evidencia de ejecución:**
```console
Starting Inventory Service
Starting Order Service
Enter product name:
PC
Checking if Inventory exists for product PC
Order has been created
Checking if order exists for product PC
Order has been executed for product PC
```

💡 Al usar `@Lazy`, Spring crea un **proxy temporal** del bean dependiente, evitando la inicialización directa y permitiendo completar el ciclo.

---

## 🧩 Solución de diseño (Patrón *Mediator*)
Cuando existen dependencias circulares, generalmente **es señal de un mal diseño**.  
Una opción más limpia que usar `@Lazy` es implementar el **patrón Mediator**, que desacopla las clases involucradas y centraliza la coordinación en un nuevo servicio (por ejemplo, `SalesManagerService`).

### ✨ Código refactorizado

#### 🧱 `InventoryService`
```java
package org.loop.solution.service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public boolean checkInventory(String product) {
        System.out.println("Checking if Inventory exists for product " + product);
        return true;
    }

    public void verifyIfOrderExits(String product) {
        System.out.println("Order has been executed for product " + product);
    }
}
```

#### ⚙️ `OrderService`
```java
package org.loop.solution.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void createOrder(String product) {
        System.out.println("Order has been created");
    }

    public boolean verifyOrder(String product) {
        System.out.println("Checking if order exists for product " + product);
        return true;
    }
}
```

#### 🧭 `SalesManagerService`
```java
package org.loop.solution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesManagerService {
    private final InventoryService inventoryService;
    private final OrderService orderService;

    @Autowired
    public SalesManagerService(InventoryService inventoryService, OrderService orderService) {
        this.inventoryService = inventoryService;
        this.orderService = orderService;
    }

    public void createOrder(String product) {
        if (inventoryService.checkInventory(product)) {
            orderService.createOrder(product);
        } else {
            System.out.println("Order has not been created, out of stock");
        }
    }

    public void verifyIfOrderExits(String product) {
        if (orderService.verifyOrder(product)) {
            inventoryService.verifyIfOrderExits(product);
        } else {
            System.out.println("Failed to verify if order exits");
        }
    }
}
```

#### 🚀 `Runner`
```java
package org.loop.solution;

import org.loop.solution.service.SalesManagerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan
public class Runner {

    static void main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);

        Scanner scanner = new Scanner(System.in);
        SalesManagerService salesManagerService = context.getBean(SalesManagerService.class);

        System.out.println("Enter product name:");
        String productName = scanner.next();

        salesManagerService.createOrder(productName);
        salesManagerService.verifyIfOrderExits(productName);
    }
}
```

**🧾 Resultado en consola:**
```console
Enter product name:
laptop
Checking if Inventory exists for product laptop
Order has been created
Checking if order exists for product laptop
Order has been executed for product laptop
```

💬 Esta solución elimina la dependencia circular y mantiene las clases desacopladas, aunque **puede comprometer el principio de responsabilidad única**.

---

## 🧩 Conclusión
El error `BeanCurrentlyInCreationException` es uno de los más comunes al trabajar con servicios interdependientes.  
Para evitarlo, se recomienda:

- 🔍 Analizar las relaciones de dependencia antes del diseño final.
- 🧱 Sustituir la inyección por constructor por *setter* en uno de los beans.
- 💤 Usar `@Lazy` para retrasar la carga del bean dependiente.
- 🧭 Refactorizar la lógica con patrones de diseño como **Mediator** para dividir responsabilidades.

✅ Con esta modificación, el contexto de Spring se inicializa correctamente y se evita el **bucle de creación infinita.**
