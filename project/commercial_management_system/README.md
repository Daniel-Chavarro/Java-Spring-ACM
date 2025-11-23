# Commercial Management System

Un sistema de gestión comercial desarrollado en **Java Spring Boot** con arquitectura multicapa, diseñado para gestionar tiendas, productos, categorías, usuarios y ventas.

---

## 📋 Tabla de Contenidos

- [Descripción General](#descripción-general)
- [Tecnologías](#tecnologías)
- [Arquitectura](#arquitectura)
  - [Entidades (JPA)](#entidades-jpa)
  - [Repositorios](#repositorios)
  - [Servicios](#servicios)
  - [Controladores](#controladores)
  - [Mappers (MapStruct)](#mappers-mapstruct)
- [Diagrama de Relaciones](#diagrama-de-relaciones)
- [Endpoints de la API](#-endpoints-de-la-api)
  - [Categories](#1-categories-controller-apiv1categories)
  - [Cities](#2-cities-controller-apiv1cities)
  - [Departments](#3-departments-controller-apiv1departments)
  - [Products](#4-products-controller-apiv1products)
  - [Stores](#5-stores-controller-apiv1stores)
  - [Store Products](#6-store-products-controller-apiv1store-products)
  - [Users](#7-users-controller-apiv1users)
  - [User Roles](#8-user-roles-controller-apiv1user-roles)
  - [Sales](#9-sales-controller-apiv1sales)
  - [Sale Products](#10-sale-products-controller-apiv1sale-products)
- [Códigos de Respuesta HTTP](#-códigos-de-respuesta-http)
- [Ejemplos de Uso (cURL)](#-ejemplos-de-uso-curl)
- [Configuración](#configuración)
- [Instalación y Ejecución](#instalación-y-ejecución)

---

## Descripción General

El **Commercial Management System** es una API REST que proporciona una solución completa para:

- 🏪 **Gestión de Tiendas**: Crear, actualizar y administrar múltiples ubicaciones de tiendas
- 📦 **Gestión de Productos**: Administrar catálogo de productos con categorías
- 👥 **Gestión de Usuarios**: Crear y gestionar usuarios, roles y permisos
- 💰 **Gestión de Ventas**: Registrar transacciones de venta y líneas de productos vendidos
- 📍 **Organización Geográfica**: Gestionar ciudades, departamentos y ubicaciones de tiendas
- 📊 **Inventario**: Controlar stock de productos por tienda y ubicación física

---

## Tecnologías

| Tecnología | Versión | Propósito |
|-----------|---------|----------|
| **Java** | 21 | Lenguaje de programación |
| **Spring Boot** | 3.5.7 | Framework principal |
| **Spring Data JPA** | 3.5.7 | ORM y acceso a datos |
| **MapStruct** | 1.6.3 | Mapeo de entidades a DTOs |
| **Lombok** | (3.5.7) | Generación de boilerplate |
| **Jakarta Persistence** | (3.5.7) | API de persistencia |
| **Maven** | 3.x | Gestor de dependencias |

---

## Arquitectura

El proyecto sigue una arquitectura **multicapa en 5 niveles**:

```
┌─────────────────────────────────────┐
│      REST Controllers (@)           │  ← Capa de Presentación
├─────────────────────────────────────┤
│      Services                       │  ← Capa de Lógica de Negocio
├─────────────────────────────────────┤
│      Mappers (MapStruct)            │  ← Capa de Transformación
├─────────────────────────────────────┤
│      Repositories (JPA)             │  ← Capa de Acceso a Datos
├─────────────────────────────────────┤
│      Entities (JPA)                 │  ← Capa de Persistencia
└─────────────────────────────────────┘
         Base de Datos (BD)
```

### Entidades (JPA)

Las entidades son objetos Java anotados con JPA que representan las tablas de la base de datos. Cada entidad está mapeada a una tabla usando `@Entity` y `@Table`.

#### **1. CityEntity**
```
Tabla: city
┌──────────────┬──────────────────────┐
│ city_id (PK) │ Identificador único  │
│ city_name    │ Nombre de la ciudad  │
│ department   │ FK → DepartmentEntity│
└──────────────┴──────────────────────┘
```

**Relaciones:**
- **ManyToOne**: Pertenece a `DepartmentEntity`
- **OneToMany**: Contiene múltiples `StoreEntity`

**Propósito:** Organizar geográficamente usuarios y tiendas dentro de departamentos.

---

#### **2. DepartmentEntity**
```
Tabla: department
┌────────────────────┬─────────────────────┐
│ department_id (PK) │ Identificador único │
│ department_name    │ Nombre del depto    │
└────────────────────┴─────────────────────┘
```

**Relaciones:**
- **OneToMany**: Contiene múltiples `CityEntity`

**Propósito:** Agrupar ciudades en unidades administrativas.

---

#### **3. StoreEntity**
```
Tabla: store
┌──────────────┬──────────────────────┐
│ store_id (PK)│ UUID, identificador  │
│ store_name   │ Nombre de la tienda  │
│ city (FK)    │ → CityEntity         │
└──────────────┴──────────────────────┘
```

**Relaciones:**
- **ManyToOne**: Ubicada en una `CityEntity`
- **OneToMany**: Contiene múltiples `StoreProductEntity`

**Propósito:** Representar ubicaciones físicas o virtuales donde se venden productos.

---

#### **4. ProductEntity**
```
Tabla: product
┌──────────────────┬──────────────────────────────┐
│ product_id (PK)  │ UUID, identificador único    │
│ product_name     │ Nombre del producto (máx 128)│
│ description      │ Descripción detallada        │
│ price            │ Precio del producto          │
│ created_at       │ Timestamp de creación        │
│ updated_at       │ Timestamp de actualización   │
│ categories (M2M) │ → CategoryEntity             │
└──────────────────┴──────────────────────────────┘
```

**Relaciones:**
- **ManyToMany**: Pertenece a múltiples `CategoryEntity`
- **OneToMany**: Contenido en múltiples `StoreProductEntity`
- **OneToMany**: Incluido en múltiples `SaleProductEntity`

**Propósito:** Representar artículos vendibles en el sistema.

---

#### **5. CategoryEntity**
```
Tabla: category
┌────────────────┬──────────────────────┐
│ category_id(PK)│ Identificador único  │
│ category_name  │ Nombre de categoría  │
│                │ (único, máx 64 chars)│
│ products (M2M) │ → ProductEntity      │
└────────────────┴──────────────────────┘
```

**Tabla Intermedia:** `product_category`
```
┌──────────────────┬──────────────────┐
│ category_id (FK) │ Ref a CategoryEnt │
│ product_id (FK)  │ Ref a ProductEnt  │
└──────────────────┴──────────────────┘
```

**Relaciones:**
- **ManyToMany**: Agrupa múltiples `ProductEntity`

**Propósito:** Clasificar productos por tipos o categorías.

---

#### **6. UserEntity**
```
Tabla: users
┌──────────────────┬──────────────────────────┐
│ user_id (PK)     │ UUID, identificador      │
│ first_name       │ Nombre (máx 32 chars)    │
│ last_name        │ Apellido (máx 32 chars)  │
│ username         │ Usuario único            │
│ email            │ Email único              │
│ password         │ Contraseña encriptada    │
│ phone            │ Teléfono (máx 10 chars)  │
│ created_at       │ Timestamp de creación    │
│ city (FK)        │ → CityEntity             │
│ role (FK)        │ → UserRoleEntity         │
└──────────────────┴──────────────────────────┘
```

**Relaciones:**
- **ManyToOne**: Ubicado en una `CityEntity`
- **ManyToOne**: Tiene un rol `UserRoleEntity`
- **OneToMany**: Realiza múltiples `SaleEntity`

**Propósito:** Representar clientes o empleados del sistema.

---

#### **7. UserRoleEntity**
```
Tabla: user_role
┌──────────────┬──────────────────────┐
│ role_id (PK) │ Identificador único  │
│ role_name    │ Nombre del rol único │
│ description  │ Descripción del rol  │
└──────────────┴──────────────────────┘
```

**Relaciones:**
- **OneToMany**: Asignado a múltiples `UserEntity`

**Propósito:** Definir roles y permisos en el sistema.

---

#### **8. StoreProductEntity**
```
Tabla: store_product
┌────────────────────┬──────────────────────────────┐
│ store_product_id   │ Identificador único          │
│ (PK)               │                              │
│ stock              │ Cantidad en stock            │
│ address            │ Ubicación dentro de la tienda│
│ store (FK)         │ → StoreEntity                │
│ product (FK)       │ → ProductEntity              │
└────────────────────┴──────────────────────────────┘
```

**Relaciones:**
- **ManyToOne**: Vincula `StoreEntity` y `ProductEntity`

**Propósito:** Gestionar inventario (stock) de productos en cada tienda.

---

#### **9. SaleEntity**
```
Tabla: sale
┌──────────────┬──────────────────────┐
│ sale_id (PK) │ UUID, identificador  │
│ sale_date    │ Fecha/hora de venta  │
│ total_amount │ Monto total (Long)   │
│ user (FK)    │ → UserEntity         │
└──────────────┴──────────────────────┘
```

**Relaciones:**
- **ManyToOne**: Realizada por un `UserEntity`
- **OneToMany**: Contiene múltiples `SaleProductEntity`

**Propósito:** Registrar transacciones de venta.

---

#### **10. SaleProductEntity**
```
Tabla: sale_product
┌────────────────────┬──────────────────────────┐
│ sale_product_id    │ Identificador único      │
│ (PK)               │                          │
│ quantity           │ Cantidad vendida         │
│ unit_price         │ Precio unitario          │
│ sale (FK)          │ → SaleEntity             │
│ product (FK)       │ → ProductEntity          │
└────────────────────┴──────────────────────────┘
```

**Relaciones:**
- **ManyToOne**: Pertenece a una `SaleEntity`
- **ManyToOne**: Referencia un `ProductEntity`

**Propósito:** Detallar productos incluidos en cada venta.


---

### Repositorios

Los repositorios son interfaces que extienden `JpaRepository` y proporcionan operaciones de acceso a datos (CRUD + queries personalizadas).

#### **1. CategoryRepository**
```java
interface CategoryRepository extends JpaRepository<CategoryEntity, Long>
```

**Métodos disponibles:**
- `findAll()` - Obtener todas las categorías
- `findById(Long id)` - Buscar por ID
- `save(CategoryEntity)` - Crear/actualizar
- `delete(CategoryEntity)` - Eliminar

**Propósito:** Acceso a datos de categorías de productos.

---

#### **2. CityRepository**
```java
interface CityRepository extends JpaRepository<CityEntity, Long>
```

**Métodos disponibles:**
- Heredados de JpaRepository
- Métodos custom: `findByDepartment_DepartmentId()`, `findByDepartment_DepartmentName()`

**Propósito:** Acceso a datos de ciudades.

---

#### **3. DepartmentRepository**
```java
interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>
```

**Métodos disponibles:**
- Heredados de JpaRepository
- Métodos custom: `findByDepartmentName()`

**Propósito:** Acceso a datos de departamentos.

---

#### **4. ProductRepository**
```java
interface ProductRepository extends JpaRepository<ProductEntity, UUID>
```

**Métodos disponibles:**
- Heredados de JpaRepository
- Métodos custom: `findByProductName()`, `findByCategories_CategoryId()`

**Propósito:** Acceso a datos de productos.

---

#### **5. StoreRepository**
```java
interface StoreRepository extends JpaRepository<StoreEntity, UUID>
```

**Métodos disponibles:**
- `findByCity_CityId(Long cityCityId)` - Tiendas por ID de ciudad
- `findByCity_CityName(String cityCityName)` - Tiendas por nombre de ciudad
- `findProductsByStoreId(@Param("storeId") UUID storeId)` - Productos disponibles en una tienda
- `findProductsByStoreName(@Param("storeName") String storeName)` - Productos por nombre de tienda

**Queries personalizadas con @Query:**
```java
@Query("SELECT sp.product FROM StoreProductEntity sp WHERE sp.store.storeId = :storeId")
List<ProductEntity> findProductsByStoreId(@Param("storeId") UUID storeId);
```

**Propósito:** Acceso a datos de tiendas e inventario.

---

#### **6. StoreProductRepository**
```java
interface StoreProductRepository extends JpaRepository<StoreProductEntity, Long>
```

**Métodos disponibles:**
- Heredados de JpaRepository
- Métodos custom: `findByStore_StoreId()`, `findByProduct_ProductId()`

**Propósito:** Acceso a datos del inventario (relación tienda-producto).

---

#### **7. SaleRepository**
```java
interface SaleRepository extends JpaRepository<SaleEntity, UUID>
```

**Métodos disponibles:**
- `findByUser_UserId(UUID userId)` - Ventas por usuario
- `findBySaleDateBetween()` - Ventas por rango de fechas

**Propósito:** Acceso a datos de ventas.

---

#### **8. SaleProductRepository**
```java
interface SaleProductRepository extends JpaRepository<SaleProductEntity, Long>
```

**Métodos disponibles:**
- Heredados de JpaRepository
- Métodos custom: `findBySale_SaleId()`, `findByProduct_ProductId()`

**Propósito:** Acceso a datos de líneas de venta.

---

#### **9. UserRepository**
```java
interface UserRepository extends JpaRepository<UserEntity, UUID>
```

**Métodos disponibles:**
- `findByUsername(String username)` - Buscar usuario por nombre de usuario
- `findByEmail(String email)` - Buscar usuario por email
- `findByCity_CityId(Long cityId)` - Usuarios de una ciudad

**Propósito:** Acceso a datos de usuarios.

---

#### **10. UserRoleRepository**
```java
interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long>
```

**Métodos disponibles:**
- Heredados de JpaRepository
- Métodos custom: `findByRoleName()`

**Propósito:** Acceso a datos de roles de usuario.

---

### Servicios

Los servicios contienen la lógica de negocio y sirven como intermediarios entre controladores y repositorios.

#### **Patrón de Inyección de Dependencias**

```java
@Service
public class StoreService {
    
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;
    private final ProductMapper productMapper;
    private final StoreProductMapper storeProductMapper;
    
    @Autowired
    public StoreService(StoreRepository storeRepository,
                       StoreMapper storeMapper,
                       ProductMapper productMapper,
                       StoreProductMapper storeProductMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
        this.productMapper = productMapper;
        this.storeProductMapper = storeProductMapper;
    }
}
```

**Servicios disponibles:**

| Servicio | Responsabilidades |
|----------|-------------------|
| **CategoryService** | Gestión de categorías de productos |
| **CityService** | Gestión de ciudades |
| **DepartmentService** | Gestión de departamentos |
| **ProductService** | Gestión de productos |
| **SaleService** | Gestión de ventas |
| **SaleProductService** | Gestión de líneas de venta |
| **StoreService** | Gestión de tiendas e inventario |
| **StoreProductService** | Gestión de inventario por tienda |
| **UserService** | Gestión de usuarios |
| **UserRoleService** | Gestión de roles |

#### **Métodos Típicos de Servicio**

```java
// CRUD Básico
public List<StoreModel> getAllStores();
public Optional<StoreModel> getStoreById(UUID id);
public StoreModel saveStore(StoreModel model);
public StoreModel updateStore(UUID id, StoreModel model);
public void deleteStoreById(UUID id);

// Métodos de Consulta
public List<StoreModel> getStoresByCityId(Long cityId);
public List<StoreModel> getStoresByCityName(String cityName);
public List<ProductModel> getProductsByStoreId(UUID storeId);
public List<ProductModel> getProductsByStoreName(String storeName);
```

---

### Controladores

Los controladores REST exponen los endpoints de la API y manejan las solicitudes HTTP.

#### **Estructura General de un Controlador**

```java
@RestController
@RequestMapping("/api/v1/{recurso}")
@CrossOrigin(origins = "*")
public class RecursoController {
    
    private final {RecursoService service;
    
    @Autowired
    public {RecursoController({RecursoService service) {
        this.service = service;
    }
    
    // Endpoints...
}
```

#### **Controladores Disponibles**

| Controlador | Puerto Base | Operaciones |
|-------------|------------|-------------|
| **CategoryController** | `/api/v1/categories` | CRUD de categorías |
| **CityController** | `/api/v1/cities` | CRUD de ciudades |
| **DepartmentController** | `/api/v1/departments` | CRUD de departamentos |
| **ProductController** | `/api/v1/products` | CRUD de productos |
| **SaleController** | `/api/v1/sales` | CRUD de ventas |
| **SaleProductController** | `/api/v1/sale-products` | CRUD de líneas de venta |
| **StoreController** | `/api/v1/stores` | CRUD de tiendas |
| **StoreProductController** | `/api/v1/store-products` | CRUD de inventario |
| **UserController** | `/api/v1/users` | CRUD de usuarios |
| **UserRoleController** | `/api/v1/user-roles` | CRUD de roles |

#### **Ejemplo: StoreController**

```java
@RestController
@RequestMapping("/api/v1/stores")
@CrossOrigin(origins = "*")
public class StoreController {
    
    // GET /api/v1/stores
    @GetMapping
    public ResponseEntity<List<StoreModel>> getAllStores()
    
    // GET /api/v1/stores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<StoreModel> getStoreById(@PathVariable UUID id)
    
    // POST /api/v1/stores
    @PostMapping
    public ResponseEntity<StoreModel> createStore(@RequestBody StoreModel store)
    
    // PUT /api/v1/stores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<StoreModel> updateStore(@PathVariable UUID id, 
                                                  @RequestBody StoreModel store)
    
    // DELETE /api/v1/stores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable UUID id)
    
    // GET /api/v1/stores/search/by-city-id?cityId=1
    @GetMapping("/search/by-city-id")
    public ResponseEntity<List<StoreModel>> findByCityId(@RequestParam Long cityId)
    
    // GET /api/v1/stores/search/by-city-name?cityName=Bogotá
    @GetMapping("/search/by-city-name")
    public ResponseEntity<List<StoreModel>> findByCityName(@RequestParam String cityName)
    
    // GET /api/v1/stores/search/products/by-store-id/{id}
    @GetMapping("/search/products/by-store-id/{id}")
    public ResponseEntity<List<ProductModel>> getProductsByStoreId(@PathVariable UUID id)
    
    // GET /api/v1/stores/search/products/by-store-name/{storeName}
    @GetMapping("/search/products/by-store-name/{storeName}")
    public ResponseEntity<List<ProductModel>> getProductsByStoreName(@PathVariable String storeName)
}
```

---

## 🔗 Endpoints de la API

### **1. Categories Controller** (`/api/v1/categories`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|----------|
| `GET` | `/api/v1/categories` | Obtener todas las categorías | `200 OK` |
| `GET` | `/api/v1/categories/{id}` | Obtener categoría por ID | `200 OK` / `404 Not Found` |
| `POST` | `/api/v1/categories` | Crear nueva categoría | `201 Created` |
| `PUT` | `/api/v1/categories/{id}` | Actualizar categoría | `200 OK` |
| `DELETE` | `/api/v1/categories/{id}` | Eliminar categoría | `204 No Content` |
| `GET` | `/api/v1/categories/search/by-name?categoryName=...` | Buscar por nombre | `200 OK` / `404 Not Found` |

**Ejemplo de Request POST:**
```json
{
  "categoryName": "Electrónica"
}
```

---

### **2. Cities Controller** (`/api/v1/cities`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|----------|
| `GET` | `/api/v1/cities` | Obtener todas las ciudades | `200 OK` |
| `GET` | `/api/v1/cities/{id}` | Obtener ciudad por ID | `200 OK` / `404 Not Found` |
| `POST` | `/api/v1/cities` | Crear nueva ciudad | `201 Created` |
| `PUT` | `/api/v1/cities/{id}` | Actualizar ciudad | `200 OK` |
| `DELETE` | `/api/v1/cities/{id}` | Eliminar ciudad | `204 No Content` |

**Ejemplo de Request POST:**
```json
{
  "cityName": "Bogotá",
  "department": {
    "departmentId": 1
  }
}
```

---

### **3. Departments Controller** (`/api/v1/departments`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|----------|
| `GET` | `/api/v1/departments` | Obtener todos los departamentos | `200 OK` |
| `GET` | `/api/v1/departments/{id}` | Obtener departamento por ID | `200 OK` / `404 Not Found` |
| `POST` | `/api/v1/departments` | Crear nuevo departamento | `201 Created` |
| `PUT` | `/api/v1/departments/{id}` | Actualizar departamento | `200 OK` |
| `DELETE` | `/api/v1/departments/{id}` | Eliminar departamento | `204 No Content` |

**Ejemplo de Request POST:**
```json
{
  "departmentName": "Cundinamarca"
}
```

---

### **4. Products Controller** (`/api/v1/products`)

| Método   | Endpoint                                                           | Descripción                                          | Respuesta                  |
|----------|--------------------------------------------------------------------|------------------------------------------------------|----------------------------|
| `GET`    | `/api/v1/products`                                                 | Obtener todos los productos                          | `200 OK`                   |
| `GET`    | `/api/v1/products/{id}`                                            | Obtener producto por ID                              | `200 OK` / `404 Not Found` |
| `POST`   | `/api/v1/products`                                                 | Crear nuevo producto                                 | `201 Created`              |
| `PUT`    | `/api/v1/products/{id}`                                            | Actualizar producto                                  | `200 OK`                   |
| `DELETE` | `/api/v1/products/{id}`                                            | Eliminar producto                                    | `204 No Content`           |
| `GET`    | `/api/v1/products/search/by-price-range?minPrice=...&maxPrice=...` | Buscar por rango de precio                           | `200 OK`                   |
| `GET`    | `/api/v1/products/search/sorted-by-price-asc`                      | Obtener productos ordenados por precio (ascendente)  | `200 OK`                   |
| `GET`    | `/api/v1/products/search/sorted-by-price-desc`                     | Obtener productos ordenados por precio (descendente) | `200 OK`                   |
| `GET`    | `/api/v1/products/search/recent?date=...`                          | Obtener productos creados después de una fecha       | `200 OK`                   |
| `GET`    | `/api/v1/products/analytics/best-sellers`                          | Obtener productos más vendidos                       | `200 OK`                   |
| `GET`    | `/api/v1/products/analytics/top-best-sellers?limit=...`            | Obtener top n productos más vendidos                 | `200 OK`                   |
**Ejemplo de Request POST:**
```json
{
  "productName": "Laptop Dell",
  "productDescription": "Laptop de alto rendimiento",
  "price": 1200.50,
  "categories": [
    {
      "categoryId": 1
    }
  ]
}
```

**Ejemplos de Queries:**
```
GET /api/v1/products/search/by-price-range?minPrice=100&maxPrice=1000
GET /api/v1/products/search/recent?date=2025-11-20T10:30:00
```

---

### **5. Stores Controller** (`/api/v1/stores`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|----------|
| `GET` | `/api/v1/stores` | Obtener todas las tiendas | `200 OK` |
| `GET` | `/api/v1/stores/{id}` | Obtener tienda por ID | `200 OK` / `404 Not Found` |
| `POST` | `/api/v1/stores` | Crear nueva tienda | `201 Created` |
| `PUT` | `/api/v1/stores/{id}` | Actualizar tienda | `200 OK` |
| `DELETE` | `/api/v1/stores/{id}` | Eliminar tienda | `204 No Content` |
| `GET` | `/api/v1/stores/search/by-city-id?cityId=...` | Buscar tiendas por ID de ciudad | `200 OK` |
| `GET` | `/api/v1/stores/search/by-city-name?cityName=...` | Buscar tiendas por nombre de ciudad | `200 OK` |
| `GET` | `/api/v1/stores/search/products/by-store-id/{id}` | Obtener productos disponibles en una tienda | `200 OK` |
| `GET` | `/api/v1/stores/search/products/by-store-name/{storeName}` | Obtener productos por nombre de tienda | `200 OK` |

**Ejemplo de Request POST:**
```json
{
  "storeName": "Tienda Centro",
  "city": {
    "cityId": 1
  }
}
```

**Ejemplos de Queries:**
```
GET /api/v1/stores/search/by-city-id?cityId=1
GET /api/v1/stores/search/by-city-name?cityName=Bogotá
GET /api/v1/stores/search/products/by-store-id/550e8400-e29b-41d4-a716-446655440000
GET /api/v1/stores/search/products/by-store-name/Tienda%20Centro
```

---

### **6. Store Products Controller** (`/api/v1/store-products`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|----------|
| `GET` | `/api/v1/store-products` | Obtener todas las relaciones tienda-producto | `200 OK` |
| `GET` | `/api/v1/store-products/{id}` | Obtener relación por ID | `200 OK` / `404 Not Found` |
| `POST` | `/api/v1/store-products` | Crear nueva relación tienda-producto | `201 Created` |
| `PUT` | `/api/v1/store-products/{id}` | Actualizar relación | `200 OK` |
| `DELETE` | `/api/v1/store-products/{id}` | Eliminar relación | `204 No Content` |

**Ejemplo de Request POST:**
```json
{
  "stock": 150,
  "address": "Pasillo 3, Estante 5",
  "store": {
    "storeId": "550e8400-e29b-41d4-a716-446655440000"
  },
  "product": {
    "productId": "550e8400-e29b-41d4-a716-446655440001"
  }
}
```

---

### **7. Users Controller** (`/api/v1/users`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|----------|
| `GET` | `/api/v1/users` | Obtener todos los usuarios | `200 OK` |
| `GET` | `/api/v1/users/{id}` | Obtener usuario por ID | `200 OK` / `404 Not Found` |
| `POST` | `/api/v1/users` | Crear nuevo usuario | `201 Created` |
| `PUT` | `/api/v1/users/{id}` | Actualizar usuario | `200 OK` |
| `DELETE` | `/api/v1/users/{id}` | Eliminar usuario | `204 No Content` |
| `GET` | `/api/v1/users/search/by-lastname?lastName=...` | Buscar por apellido | `200 OK` |
| `GET` | `/api/v1/users/search/by-city?cityId=...` | Buscar por ID de ciudad | `200 OK` |
| `GET` | `/api/v1/users/search/by-city-name?cityName=...` | Buscar por nombre de ciudad | `200 OK` |
| `GET` | `/api/v1/users/search/by-department?departmentName=...` | Buscar por nombre de departamento | `200 OK` |

**Ejemplo de Request POST:**
```json
{
  "firstName": "Juan",
  "lastName": "Pérez",
  "username": "jperez",
  "email": "juan.perez@example.com",
  "password": "hashedPassword123",
  "phone": "3001234567",
  "city": {
    "cityId": 1
  },
  "role": {
    "userRoleId": 1
  }
}
```

**Ejemplos de Queries:**
```
GET /api/v1/users/search/by-lastname?lastName=Pérez
GET /api/v1/users/search/by-city?cityId=1
GET /api/v1/users/search/by-city-name?cityName=Bogotá
GET /api/v1/users/search/by-department?departmentName=Cundinamarca
```

---

### **8. User Roles Controller** (`/api/v1/user-roles`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|----------|
| `GET` | `/api/v1/user-roles` | Obtener todos los roles | `200 OK` |
| `GET` | `/api/v1/user-roles/{id}` | Obtener rol por ID | `200 OK` / `404 Not Found` |
| `POST` | `/api/v1/user-roles` | Crear nuevo rol | `201 Created` |
| `PUT` | `/api/v1/user-roles/{id}` | Actualizar rol | `200 OK` |
| `DELETE` | `/api/v1/user-roles/{id}` | Eliminar rol | `204 No Content` |

**Ejemplo de Request POST:**
```json
{
  "roleName": "ADMIN",
  "description": "Administrador del sistema"
}
```

---

### **9. Sales Controller** (`/api/v1/sales`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|----------|
| `GET` | `/api/v1/sales` | Obtener todas las ventas | `200 OK` |
| `GET` | `/api/v1/sales/{id}` | Obtener venta por ID | `200 OK` / `404 Not Found` |
| `POST` | `/api/v1/sales` | Crear nueva venta | `201 Created` |
| `PUT` | `/api/v1/sales/{id}` | Actualizar venta | `200 OK` |
| `DELETE` | `/api/v1/sales/{id}` | Eliminar venta | `204 No Content` |
| `GET` | `/api/v1/sales/search/by-user-id?userId=...` | Buscar ventas por usuario | `200 OK` |
| `GET` | `/api/v1/sales/search/by-user-firstname?firstName=...` | Buscar ventas por nombre de usuario | `200 OK` |
| `GET` | `/api/v1/sales/search/by-min-total-amount?amount=...` | Buscar ventas con monto mínimo | `200 OK` |
| `GET` | `/api/v1/sales/analytics/total-by-date?date=...` | Obtener total de ventas por fecha | `200 OK` |

**Ejemplo de Request POST:**
```json
{
  "totalAmount": 5000,
  "user": {
    "userId": "550e8400-e29b-41d4-a716-446655440000"
  }
}
```

**Ejemplos de Queries:**
```
GET /api/v1/sales/search/by-user-id?userId=550e8400-e29b-41d4-a716-446655440000
GET /api/v1/sales/search/by-user-firstname?firstName=Juan
GET /api/v1/sales/search/by-min-total-amount?amount=1000
GET /api/v1/sales/analytics/total-by-date?date=2025-11-20T10:30:00
```

---

### **10. Sale Products Controller** (`/api/v1/sale-products`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|----------|
| `GET` | `/api/v1/sale-products` | Obtener todas las líneas de venta | `200 OK` |
| `GET` | `/api/v1/sale-products/{id}` | Obtener línea de venta por ID | `200 OK` / `404 Not Found` |
| `POST` | `/api/v1/sale-products` | Crear nueva línea de venta | `201 Created` |
| `PUT` | `/api/v1/sale-products/{id}` | Actualizar línea de venta | `200 OK` |
| `DELETE` | `/api/v1/sale-products/{id}` | Eliminar línea de venta | `204 No Content` |

**Ejemplo de Request POST:**
```json
{
  "quantity": 3,
  "unitPrice": 1200.50,
  "sale": {
    "saleId": "550e8400-e29b-41d4-a716-446655440000"
  },
  "product": {
    "productId": "550e8400-e29b-41d4-a716-446655440001"
  }
}
```

---

## 📊 Códigos de Respuesta HTTP

| Código | Significado | Ejemplo |
|--------|------------|---------|
| `200 OK` | Solicitud exitosa | GET, PUT exitosos |
| `201 Created` | Recurso creado exitosamente | POST exitoso |
| `204 No Content` | Solicitud exitosa sin contenido | DELETE exitoso |
| `400 Bad Request` | Solicitud inválida | Datos malformados en JSON |
| `404 Not Found` | Recurso no encontrado | ID inexistente |
| `500 Internal Server Error` | Error del servidor | Error no controlado |

---

## 🧪 Ejemplos de Uso (cURL)

### Crear una categoría:
```bash
curl -X POST http://localhost:8080/api/v1/categories \
  -H "Content-Type: application/json" \
  -d '{
    "categoryName": "Electrónica"
  }'
```

### Obtener todas las tiendas:
```bash
curl http://localhost:8080/api/v1/stores
```

### Crear un usuario:
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Juan",
    "lastName": "Pérez",
    "username": "jperez",
    "email": "juan.perez@example.com",
    "password": "password123",
    "phone": "3001234567",
    "city": {
      "cityId": 1
    },
    "role": {
      "userRoleId": 1
    }
  }'
```

### Buscar productos por rango de precio:
```bash
curl "http://localhost:8080/api/v1/products/search/by-price-range?minPrice=100&maxPrice=1000"
```

### Obtener ventas por usuario:
```bash
curl "http://localhost:8080/api/v1/sales/search/by-user-id?userId=550e8400-e29b-41d4-a716-446655440000"
```

---

### Mappers (MapStruct)

Los mappers son interfaces que automatizan la conversión entre entidades y DTOs (Data Transfer Objects) usando MapStruct.

#### **¿Por qué usar Mappers?**

1. **Separación de Responsabilidades**: Las entidades (modelos BD) no se exponen directamente al cliente
2. **Control de Datos**: Se puede controlar qué campos se envían/reciben
3. **Flexibilidad**: Cambios en BD no afectan la API
4. **Rendimiento**: Se puede optimizar qué relaciones se cargan

#### **Estructura de un Mapper**

```java
@Mapper(componentModel = "spring", uses = {OtherMapper.class})
public interface StoreMapper {
    
    // Entidad → DTO
    StoreModel toModel(StoreEntity entity);
    
    // DTO → Entidad
    StoreEntity toEntity(StoreModel model);
    
    // Listas
    List<StoreModel> toModelList(List<StoreEntity> entities);
    List<StoreEntity> toEntityList(List<StoreModel> models);
    
    // Actualización (merge)
    void updateEntityFromModel(StoreModel model, @MappingTarget StoreEntity entity);
}
```

**Parámetros de @Mapper:**
- `componentModel = "spring"`: Crea un bean Spring
- `uses = {OtherMapper.class}`: Inyecta otros mappers para relaciones

#### **Mappers Disponibles**

| Mapper | Entidad ↔ Modelo |
|--------|-----------------|
| **CategoryMapper** | CategoryEntity ↔ CategoryModel |
| **CityMapper** | CityEntity ↔ CityModel |
| **DepartmentMapper** | DepartmentEntity ↔ DepartmentModel |
| **ProductMapper** | ProductEntity ↔ ProductModel |
| **SaleMapper** | SaleEntity ↔ SaleModel |
| **SaleProductMapper** | SaleProductEntity ↔ SaleProductModel |
| **StoreMapper** | StoreEntity ↔ StoreModel |
| **StoreProductMapper** | StoreProductEntity ↔ StoreProductModel |
| **UserMapper** | UserEntity ↔ UserModel |
| **UserRoleMapper** | UserRoleEntity ↔ UserRoleModel |

#### **Ejemplo de Mapeo**

```java
// Entrada JSON (API)
{
  "storeName": "Tienda Centro",
  "city": {
    "cityId": 1,
    "cityName": "Bogotá"
  }
}

// ↓ (Mapper convierte)

// StoreModel (DTO)
@Data
public class StoreModel {
    private UUID storeId;
    private String storeName;
    private CityModel city;
}

// ↓ (Mapper convierte)

// StoreEntity (Entidad JPA)
@Entity
public class StoreEntity {
    @Id
    private UUID storeId;
    @Column(name = "store_name")
    private String storeName;
    @ManyToOne
    @JoinColumn(name = "city_id_fk")
    private CityEntity city;
}
```

---

## Configuración

### application.properties

```properties
# Configuración de Base de Datos
spring.datasource.url=jdbc:mysql://localhost:3306/commercial_management_system
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Configuración de Servidor
server.port=8080
server.servlet.context-path=/
```

### Estructura de Directorios

```
src/main/java/org/acmapis/commercial_management_system/
├── CommercialManagementSystemApplication.java    (Main class)
├── controller/                                    (REST Endpoints)
│   ├── CategoryController.java
│   ├── CityController.java
│   ├── DepartmentController.java
│   ├── ProductController.java
│   ├── SaleController.java
│   ├── SaleProductController.java
│   ├── StoreController.java
│   ├── StoreProductController.java
│   ├── UserController.java
│   └── UserRoleController.java
├── entity/                                        (JPA Entities)
│   ├── CategoryEntity.java
│   ├── CityEntity.java
│   ├── DepartmentEntity.java
│   ├── ProductEntity.java
│   ├── SaleEntity.java
│   ├── SaleProductEntity.java
│   ├── StoreEntity.java
│   ├── StoreProductEntity.java
│   ├── UserEntity.java
│   └── UserRoleEntity.java
├── model/                                         (DTOs)
│   └── dto/
│       ├── CategoryModel.java
│       ├── CityModel.java
│       ├── DepartmentModel.java
│       ├── ProductModel.java
│       ├── SaleModel.java
│       ├── SaleProductModel.java
│       ├── StoreModel.java
│       ├── StoreProductModel.java
│       ├── UserModel.java
│       └── UserRoleModel.java
├── repository/                                    (Data Access)
│   ├── CategoryRepository.java
│   ├── CityRepository.java
│   ├── DepartmentRepository.java
│   ├── ProductRepository.java
│   ├── SaleProductRepository.java
│   ├── SaleRepository.java
│   ├── StoreProductRepository.java
│   ├── StoreRepository.java
│   ├── UserRepository.java
│   └── UserRoleRepository.java
├── service/                                       (Business Logic)
│   ├── CategoryService.java
│   ├── CityService.java
│   ├── DepartmentService.java
│   ├── ProductService.java
│   ├── SaleProductService.java
│   ├── SaleService.java
│   ├── StoreProductService.java
│   ├── StoreService.java
│   ├── UserRoleService.java
│   └── UserService.java
└── utils/                                         (Utilities)
    ├── config/                                    (Configuration classes)
    └── mapper/                                    (MapStruct Mappers)
        ├── CategoryMapper.java
        ├── CityMapper.java
        ├── DepartmentMapper.java
        ├── ProductMapper.java
        ├── SaleMapper.java
        ├── SaleProductMapper.java
        ├── StoreMapper.java
        ├── StoreProductMapper.java
        ├── UserMapper.java
        └── UserRoleMapper.java

src/main/resources/
├── application.properties                        (Configuration)
├── static/                                        (Static files)
└── templates/                                     (Templates)
```

---

## Instalación y Ejecución

### Requisitos Previos

- **Java 21+** instalado
- **Maven 3.8+** instalado
- **MySQL 8.0+** instalado y ejecutándose
- **Git** instalado

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/commercial-management-system.git
   cd commercial-management-system
   ```

2. **Configurar la Base de Datos**
   ```sql
   CREATE DATABASE commercial_management_system;
   USE commercial_management_system;
   ```

3. **Configurar credentials en application.properties**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/commercial_management_system
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

4. **Compilar el proyecto**
   ```bash
   mvn clean install
   ```

5. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```
   
   O si usa Maven en Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

6. **Verificar que está ejecutándose**
   ```
   http://localhost:8080
   ```

### Docker (Opcional)

```bash
docker-compose up -d
```

---

## Flujo de Datos Típico

```
1. Cliente (Browser/Postman)
        ↓ (HTTP Request: POST /api/v1/stores)
        ↓ JSON Payload
2. REST Controller (StoreController)
        ↓ (Recibe StoreModel)
        ↓ (Valida datos)
3. Service (StoreService)
        ↓ (Lógica de negocio)
        ↓ (Convierte Model → Entity usando Mapper)
4. Mapper (StoreMapper)
        ↓ (Transforma DTO a Entidad JPA)
5. Repository (StoreRepository)
        ↓ (Persiste en BD)
        ↓ (SQL: INSERT INTO store...)
6. Database (MySQL)
        ↓ (Almacena registro)
7. Repository (retorna)
        ↓ (Devuelve StoreEntity)
8. Mapper (retorna)
        ↓ (Convierte Entity → Model)
9. Service (retorna)
        ↓ (Retorna StoreModel)
10. Controller (retorna)
        ↓ (ResponseEntity.status(201))
11. Cliente
        ↓ (HTTP Response 201 Created)
        ↓ (JSON Payload con datos creados)
```

---

## Características Principales

✅ **Arquitectura Multicapa**: Separación clara de responsabilidades
✅ **RESTful API**: Endpoints HTTP estándar
✅ **JPA + Hibernate**: ORM robusto y flexible
✅ **MapStruct**: Mapeo automático de entidades a DTOs
✅ **Validación**: Validación de datos en entrada
✅ **Manejo de Excepciones**: Errores HTTP estándar
✅ **Queries Personalizadas**: @Query para consultas complejas
✅ **Relaciones Complejas**: ManyToMany, OneToMany, ManyToOne
✅ **Timestamps Automáticos**: Auditoría de creación/actualización
✅ **CORS Habilitado**: Soporte para clientes externos

---

## Próximas Mejoras Planificadas

- [ ] Autenticación JWT
- [ ] Autorización basada en roles
- [ ] Paginación en endpoints
- [ ] Filtros avanzados
- [ ] Documentación Swagger
- [ ] Tests unitarios
- [ ] Tests de integración
- [ ] Validación de entrada con @Valid
- [ ] Manejo centralizado de excepciones
- [ ] Logging estructurado

---

## Contribución

Las contribuciones son bienvenidas. Por favor:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

## Licencia

Este proyecto está bajo la Licencia GNU GPL.

---

**Última actualización:** Noviembre 22, 2025
**Versión:** 0.0.1-SNAPSHOT (y la última disponible)


