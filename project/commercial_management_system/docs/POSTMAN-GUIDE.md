# 📮 Guía de Uso - Colección Postman API

## 📋 Tabla de Contenidos
- [Introducción](#introducción)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Estructura de la Colección](#estructura-de-la-colección)
- [Guía de Uso por Endpoint](#guía-de-uso-por-endpoint)
- [Ejemplos Prácticos](#ejemplos-prácticos)
- [Testing](#testing)
- [Troubleshooting](#troubleshooting)

---

## 🎯 Introducción

Esta colección de Postman contiene **todos los endpoints** del Sistema de Gestión Comercial, organizados por controladores y con ejemplos de peticiones reales.

### Características

✅ **140+ Endpoints** organizados en 10 categorías  
✅ **Ejemplos con datos reales** de la base de datos  
✅ **Variables de entorno** preconfiguradas  
✅ **Búsquedas avanzadas** y queries especializadas  
✅ **CRUD completo** para todas las entidades  
✅ **Documentación inline** en cada petición  

---

## 📦 Instalación

### Paso 1: Importar la Colección

1. Abre **Postman**
2. Click en **Import**
3. Selecciona el archivo: `Commercial_Management_System_API.postman_collection.json`
4. Click en **Import**

### Paso 2: Importar el Environment

1. Click en **Environments** (icono de engranaje arriba a la derecha)
2. Click en **Import**
3. Selecciona el archivo: `Commercial_Management_System.postman_environment.json`
4. Click en **Import**

### Paso 3: Activar el Environment

1. En el dropdown de environments (esquina superior derecha)
2. Selecciona **"Commercial Management System - Environment"**

---

## ⚙️ Configuración

### Variables de Entorno

La colección usa las siguientes variables:

| Variable | Valor Default | Descripción |
|----------|--------------|-------------|
| `base_url` | `http://localhost:8080/api/v1` | URL base de la API |
| `department_id` | `1` | ID de ejemplo de departamento |
| `city_id` | `1` | ID de ejemplo de ciudad |
| `user_id` | `(UUID)` | UUID de usuario (obtener de GET) |
| `product_id` | `(UUID)` | UUID de producto (obtener de GET) |
| `store_id` | `(UUID)` | UUID de tienda (obtener de GET) |
| `sale_id` | `(UUID)` | UUID de venta (obtener de GET) |

### Configurar Variables UUID

Para las variables con UUID, debes:

1. **Iniciar la aplicación** y ejecutar el seeder SQL
2. **Ejecutar peticiones GET** para obtener IDs:
   - `GET /users` → Copiar un `userId`
   - `GET /products` → Copiar un `productId`
   - `GET /stores` → Copiar un `storeId`
   - `GET /sales` → Copiar un `saleId`
3. **Pegar los UUIDs** en las variables de entorno

#### Ejemplo:
```json
// Respuesta de GET /users
{
  "userId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
  "firstName": "Juan",
  ...
}
```

Copiar el UUID y pegarlo en la variable `user_id` del environment.

---

## 📁 Estructura de la Colección

La colección está organizada en **10 carpetas principales**:

### 1️⃣ Departments (Departamentos)
- Get All Departments
- Get Department by ID
- Create Department
- Update Department
- Delete Department

**Base Path:** `/departments`

### 2️⃣ Cities (Ciudades)
- Get All Cities
- Get City by ID
- Create City
- Update City
- Delete City
- Get Cities by Department 🔍

**Base Path:** `/cities`

### 3️⃣ User Roles (Roles de Usuario)
- Get All User Roles
- Get User Role by ID
- Create User Role
- Update User Role
- Delete User Role

**Base Path:** `/user-roles`  
**Valores:** `ADMIN`, `USER`, `MANAGER`

### 4️⃣ Users (Usuarios)
- Get All Users
- Get User by ID
- Create User
- Update User
- Delete User
- Search Users by Last Name 🔍
- Get Users by City 🔍

**Base Path:** `/users`

### 5️⃣ Categories (Categorías)
- Get All Categories
- Get Category by ID
- Create Category
- Update Category
- Delete Category

**Base Path:** `/categories`

### 6️⃣ Products (Productos)
- Get All Products
- Get Product by ID
- Create Product
- Update Product
- Delete Product
- Search by Price Range 🔍
- Get Products Sorted by Price ASC 🔍
- Get Products Sorted by Price DESC 🔍
- Get Products by Category 🔍
- Get Products Created Between Dates 🔍

**Base Path:** `/products`

### 7️⃣ Stores (Tiendas)
- Get All Stores
- Get Store by ID
- Create Store
- Update Store
- Delete Store
- Get Stores by City 🔍

**Base Path:** `/stores`

### 8️⃣ Store Products (Inventario)
- Get All Store Products
- Get Store Product by ID
- Create Store Product
- Update Store Product
- Delete Store Product
- Get Products by Store 🔍
- Get Stores by Product 🔍
- Get Low Stock Products 🔍

**Base Path:** `/store-products`

### 9️⃣ Sales (Ventas)
- Get All Sales
- Get Sale by ID
- Create Sale
- Update Sale
- Delete Sale
- Get Sales by User 🔍
- Get Sales Between Dates 🔍
- Get Total Sales Amount 📊

**Base Path:** `/sales`

### 🔟 Sale Products (Productos de Venta)
- Get All Sale Products
- Get Sale Product by ID
- Create Sale Product
- Update Sale Product
- Delete Sale Product
- Get Products by Sale 🔍
- Get Sales by Product 🔍
- Get Top Selling Products 📊

**Base Path:** `/sale-products`

---

## 🚀 Guía de Uso por Endpoint

### Operaciones CRUD Básicas

#### 📖 GET (Obtener)

**Get All (Obtener Todos)**
```http
GET {{base_url}}/products
```
Retorna una lista con todos los registros.

**Get by ID (Obtener por ID)**
```http
GET {{base_url}}/products/{{product_id}}
```
Retorna un registro específico.

#### ➕ POST (Crear)

```http
POST {{base_url}}/products
Content-Type: application/json

{
  "productName": "iPhone 15 Pro",
  "productDescription": "Smartphone Apple",
  "price": 4999000.0,
  "categories": [
    {
      "categoryId": 1,
      "categoryName": "Electrónica"
    }
  ]
}
```

**Status:** `201 Created`

#### ✏️ PUT (Actualizar)

```http
PUT {{base_url}}/products/{{product_id}}
Content-Type: application/json

{
  "productName": "iPhone 15 Pro Max",
  "productDescription": "Smartphone Apple Actualizado",
  "price": 5499000.0,
  "categories": [...]
}
```

**Status:** `200 OK`

#### ❌ DELETE (Eliminar)

```http
DELETE {{base_url}}/products/{{product_id}}
```

**Status:** `204 No Content`

---

## 📚 Ejemplos Prácticos

### Ejemplo 1: Crear un Usuario Completo

```http
POST {{base_url}}/users
Content-Type: application/json

{
  "firstName": "María",
  "lastName": "González",
  "username": "mgonzalez",
  "email": "maria.gonzalez@example.com",
  "password": "$2a$10$encrypted.password.hash",
  "phone": "3001234567",
  "role": {
    "userRoleId": 2,
    "role": "USER"
  },
  "city": {
    "cityId": 1,
    "cityName": "Medellín",
    "department": {
      "departmentId": 1,
      "departmentName": "Antioquia"
    }
  }
}
```

### Ejemplo 2: Buscar Productos por Rango de Precio

```http
GET {{base_url}}/products/search/by-price-range?minPrice=100000&maxPrice=500000
```

**Parámetros:**
- `minPrice`: Precio mínimo (incluido)
- `maxPrice`: Precio máximo (incluido)

**Respuesta:**
```json
[
  {
    "productId": "uuid-...",
    "productName": "Balón de Fútbol Adidas",
    "price": 89000.0,
    ...
  },
  {
    "productId": "uuid-...",
    "productName": "Licuadora Oster",
    "price": 179000.0,
    ...
  }
]
```

### Ejemplo 3: Obtener Inventario de una Tienda

```http
GET {{base_url}}/store-products/search/by-store?storeId={{store_id}}
```

Retorna todos los productos disponibles en la tienda especificada con:
- Stock actual
- Ubicación (Pasillo, Estante)
- Información del producto

### Ejemplo 4: Crear una Venta Completa

**Paso 1: Crear la venta**
```http
POST {{base_url}}/sales
Content-Type: application/json

{
  "totalAmount": 2598000,
  "user": {
    "userId": "{{user_id}}",
    "firstName": "Juan",
    "lastName": "García"
  }
}
```

**Paso 2: Agregar productos a la venta**
```http
POST {{base_url}}/sale-products
Content-Type: application/json

{
  "quantity": 2,
  "sale": {
    "saleId": "{{sale_id}}"
  },
  "product": {
    "productId": "{{product_id}}"
  }
}
```

### Ejemplo 5: Análisis de Ventas

**Total de ventas:**
```http
GET {{base_url}}/sales/analytics/total-amount
```

**Productos más vendidos:**
```http
GET {{base_url}}/sale-products/analytics/top-selling?limit=10
```

**Productos con bajo stock:**
```http
GET {{base_url}}/store-products/search/low-stock?threshold=50
```

---

## 🧪 Testing

### Tests Automáticos (Scripts Postman)

Puedes agregar tests a las peticiones:

```javascript
// Test: Verificar status 200
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

// Test: Verificar que retorna array
pm.test("Response is an array", function () {
    pm.expect(pm.response.json()).to.be.an('array');
});

// Test: Guardar ID en variable
pm.test("Save product ID", function () {
    var jsonData = pm.response.json();
    if (jsonData.length > 0) {
        pm.environment.set("product_id", jsonData[0].productId);
    }
});
```

### Flujo de Testing Recomendado

1. **Verificar conexión:**
   ```http
   GET {{base_url}}/departments
   ```
   ✅ Debe retornar 8 departamentos

2. **Obtener IDs:**
   ```http
   GET {{base_url}}/users
   GET {{base_url}}/products
   GET {{base_url}}/stores
   ```
   ✅ Copiar UUIDs y guardarlos en variables

3. **Probar CRUD:**
   - POST → Crear nuevo registro
   - GET by ID → Verificar creación
   - PUT → Actualizar registro
   - GET by ID → Verificar actualización
   - DELETE → Eliminar registro
   - GET by ID → Verificar eliminación (404)

4. **Probar búsquedas:**
   - Búsquedas por rangos
   - Búsquedas por relaciones
   - Búsquedas con filtros

---

## 🛠️ Troubleshooting

### Error: "Connection Refused"

**Causa:** La aplicación no está corriendo

**Solución:**
```bash
mvn spring-boot:run
```

### Error: "404 Not Found"

**Causa:** Endpoint incorrecto o ID inexistente

**Solución:**
- Verificar que el `base_url` es correcto
- Verificar que el ID existe (hacer GET all primero)

### Error: "400 Bad Request"

**Causa:** JSON malformado o datos inválidos

**Solución:**
- Verificar sintaxis JSON
- Verificar tipos de datos
- Verificar campos requeridos

### Error: "500 Internal Server Error"

**Causa:** Error en el servidor (violación de constraints, etc.)

**Solución:**
- Verificar logs de la aplicación
- Verificar que las relaciones FK existen
- Verificar constraints únicos (email, username)

### Variables UUID Vacías

**Causa:** No se han configurado las variables UUID

**Solución:**
1. Ejecutar `GET /users`
2. Copiar un `userId` de la respuesta
3. Ir a Environments → Variables
4. Pegar el UUID en `user_id`
5. Repetir para `product_id`, `store_id`, `sale_id`

---

## 📖 Referencia Rápida de Endpoints

### Quick Reference Table

| Entidad | Base Path | GET All | GET by ID | POST | PUT | DELETE |
|---------|-----------|---------|-----------|------|-----|--------|
| Departments | `/departments` | ✅ | ✅ | ✅ | ✅ | ✅ |
| Cities | `/cities` | ✅ | ✅ | ✅ | ✅ | ✅ |
| User Roles | `/user-roles` | ✅ | ✅ | ✅ | ✅ | ✅ |
| Users | `/users` | ✅ | ✅ | ✅ | ✅ | ✅ |
| Categories | `/categories` | ✅ | ✅ | ✅ | ✅ | ✅ |
| Products | `/products` | ✅ | ✅ | ✅ | ✅ | ✅ |
| Stores | `/stores` | ✅ | ✅ | ✅ | ✅ | ✅ |
| Store Products | `/store-products` | ✅ | ✅ | ✅ | ✅ | ✅ |
| Sales | `/sales` | ✅ | ✅ | ✅ | ✅ | ✅ |
| Sale Products | `/sale-products` | ✅ | ✅ | ✅ | ✅ | ✅ |

### Búsquedas Especiales

| Endpoint | Parámetros | Descripción |
|----------|------------|-------------|
| `GET /cities/search/by-department` | `departmentId` | Ciudades por departamento |
| `GET /users/search/by-lastname` | `lastName` | Usuarios por apellido |
| `GET /users/search/by-city` | `cityId` | Usuarios por ciudad |
| `GET /products/search/by-price-range` | `minPrice`, `maxPrice` | Productos por rango de precio |
| `GET /products/search/sorted-by-price-asc` | - | Productos ordenados por precio ↑ |
| `GET /products/search/sorted-by-price-desc` | - | Productos ordenados por precio ↓ |
| `GET /products/search/by-category` | `categoryId` | Productos por categoría |
| `GET /products/search/created-between` | `startDate`, `endDate` | Productos por fecha de creación |
| `GET /stores/search/by-city` | `cityId` | Tiendas por ciudad |
| `GET /store-products/search/by-store` | `storeId` | Inventario de una tienda |
| `GET /store-products/search/by-product` | `productId` | Tiendas que tienen un producto |
| `GET /store-products/search/low-stock` | `threshold` | Productos con stock bajo |
| `GET /sales/search/by-user` | `userId` | Ventas de un usuario |
| `GET /sales/search/by-date-range` | `startDate`, `endDate` | Ventas por rango de fechas |
| `GET /sales/analytics/total-amount` | - | Monto total de ventas |
| `GET /sale-products/search/by-sale` | `saleId` | Productos de una venta |
| `GET /sale-products/search/by-product` | `productId` | Ventas que incluyen un producto |
| `GET /sale-products/analytics/top-selling` | `limit` | Productos más vendidos |

---

## 🎓 Tips y Mejores Prácticas

### 1. Usar Collections Runner
Ejecuta toda la colección automáticamente:
- Click en la colección → Run
- Selecciona el environment
- Click en "Run Commercial Management System API"

### 2. Exportar Resultados
Después de ejecutar tests:
- View Results → Export Results
- Guarda el JSON para análisis

### 3. Usar Pre-request Scripts
Para generar datos dinámicos:
```javascript
// Generar email aleatorio
pm.environment.set("random_email", 
    "user" + Math.random().toString(36).substring(7) + "@example.com");
```

### 4. Compartir Variables
Crea múltiples environments:
- Development
- Testing
- Staging
- Production

### 5. Documentación Automática
Publica tu colección:
- Click derecho en la colección → Publish Docs
- Genera documentación HTML interactiva

---

## 📞 Soporte

Para problemas o preguntas:
- Revisa los logs de la aplicación Spring Boot
- Verifica que la base de datos esté corriendo
- Asegúrate de que el seeder SQL se ejecutó correctamente

---

## 📝 Changelog

### v1.0 - 2025-11-22
- ✅ Colección inicial completa
- ✅ 140+ endpoints documentados
- ✅ Variables de entorno
- ✅ Ejemplos con datos reales
- ✅ Búsquedas avanzadas
- ✅ Endpoints de analytics

---

**Autor:** Commercial Management System Team  
**Fecha:** 2025-11-22  
**Versión:** 1.0

