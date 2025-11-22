# 🚀 Guía Rápida de Postman - API Endpoints

## ❌ ERROR COMÚN: 415 Unsupported Media Type

### ¿Qué está mal?
Si ves este error:
```json
{
  "status": 415,
  "error": "Unsupported Media Type"
}
```

**Solución:** Estás usando el formato incorrecto en Postman.

---

## ✅ SOLUCIÓN: Usar JSON (RECOMENDADO)

### 📋 Pasos en Postman:

1. **Método:** `POST`
2. **URL:** `http://localhost:8080/api/v1/categories`
3. **Pestaña Body:**
   - ✅ Seleccionar: **raw**
   - ✅ Dropdown derecha: **JSON** (no Text!)
4. **Contenido:**
   ```json
   {
     "categoryName": "electronics"
   }
   ```
5. **Click en Send** ✅

### ✅ Respuesta Esperada (201 Created):
```json
{
  "categoryId": 1,
  "categoryName": "electronics",
  "products": null
}
```

---

## 📸 Configuración Visual de Postman

```
┌─────────────────────────────────────────────────────┐
│ POST  http://localhost:8080/api/v1/categories   Send│
├─────────────────────────────────────────────────────┤
│ Params | Authorization | Headers | Body | ...       │
│                                     ▼▼▼▼▼           │
├─────────────────────────────────────────────────────┤
│ Body                                                │
│                                                     │
│ ○ none   ○ form-data   ○ x-www-form-urlencoded     │
│ ● raw    ○ binary      ○ GraphQL                   │
│                                                     │
│ [Text ▼]  ← CAMBIAR A: [JSON ▼]  ⭐ IMPORTANTE!   │
│                                                     │
│ 1  {                                                │
│ 2    "categoryName": "electronics"                  │
│ 3  }                                                │
│                                                     │
└─────────────────────────────────────────────────────┘
```

---

## 🔧 Alternativas (También Funcionan)

### Opción 2: Query Parameters
```
┌─────────────────────────────────────────────────────┐
│ POST  http://localhost:8080/api/v1/categories   Send│
├─────────────────────────────────────────────────────┤
│ Params | Authorization | Headers | Body | ...       │
│ ▼▼▼▼▼                                               │
├─────────────────────────────────────────────────────┤
│ Params                                              │
│                                                     │
│ KEY            │ VALUE                              │
│ ──────────────────────────────────────────────      │
│ categoryName   │ electronics                        │
│                                                     │
└─────────────────────────────────────────────────────┘
```

### Opción 3: Form Data
```
┌─────────────────────────────────────────────────────┐
│ POST  http://localhost:8080/api/v1/categories   Send│
├─────────────────────────────────────────────────────┤
│ Params | Authorization | Headers | Body | ...       │
│                                     ▼▼▼▼▼           │
├─────────────────────────────────────────────────────┤
│ Body                                                │
│                                                     │
│ ○ none   ● form-data   ○ x-www-form-urlencoded     │
│ ○ raw    ○ binary      ○ GraphQL                   │
│                                                     │
│ KEY            │ VALUE                              │
│ ──────────────────────────────────────────────      │
│ categoryName   │ electronics                        │
│                                                     │
└─────────────────────────────────────────────────────┘
```

---

## 🧪 Ejemplos de Todos los Endpoints

### 1️⃣ Category
```json
POST http://localhost:8080/api/v1/categories
Body → raw → JSON

{
  "categoryName": "electronics"
}
```

### 2️⃣ Department
```json
POST http://localhost:8080/api/v1/departments
Body → raw → JSON

{
  "departmentName": "Antioquia"
}
```

### 3️⃣ City
```json
POST http://localhost:8080/api/v1/cities
Body → raw → JSON

{
  "cityName": "Medellin",
  "department": {
    "departmentId": 1
  }
}
```

---

## ✅ Checklist de Verificación

Antes de hacer clic en "Send", verifica:

- [ ] El método es **POST** (no GET)
- [ ] La URL es correcta (incluye `/api/v1/...`)
- [ ] En Body, está seleccionado **raw**
- [ ] El dropdown dice **JSON** (no Text)
- [ ] El JSON está bien formado (usa comillas dobles `"`)
- [ ] Los nombres de campos son correctos (ej: `categoryName`)

---

## 🚨 Errores Comunes y Soluciones

### Error 415 - Unsupported Media Type
**Causa:** Body está en "form-data" o "Text" en vez de "JSON"
**Solución:** Cambiar a raw → JSON

### Error 400 - Bad Request
**Causa:** Campo vacío, mal formado, o faltante
**Solución:** Verificar que el JSON tenga el campo requerido con valor

### Error 404 - Not Found
**Causa:** URL incorrecta
**Solución:** Verificar que la URL sea exacta: `/api/v1/categories`

### Error 500 - Internal Server Error
**Causa:** Problema en el servidor (ej: base de datos)
**Solución:** Verificar que la aplicación esté corriendo y la BD conectada

---

## 💡 Tip Pro: Colección de Postman

Crea una colección con estos endpoints pre-configurados:

```
📁 Commercial Management System API
  ├─ 📂 Categories
  │   ├─ POST Create Category (JSON)
  │   ├─ POST Create Category (Query Params)
  │   ├─ GET All Categories
  │   └─ GET Category by ID
  ├─ 📂 Departments
  │   ├─ POST Create Department (JSON)
  │   └─ ...
  └─ 📂 Cities
      ├─ POST Create City (JSON)
      └─ ...
```

---

## 🎯 Resumen

### Para Crear una Categoría:
1. **POST** a `http://localhost:8080/api/v1/categories`
2. **Body → raw → JSON**
3. Contenido: `{"categoryName": "valor"}`
4. **Send**

### ¡Eso es todo! 🎉

Si sigues estos pasos, **no tendrás más errores 415**.

---

## 📚 Más Información

Para detalles completos, ver: `api-post-endpoints-usage.md`

