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
