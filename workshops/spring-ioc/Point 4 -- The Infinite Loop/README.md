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
