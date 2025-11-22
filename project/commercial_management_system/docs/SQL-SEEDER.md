# Database Seeder SQL - Guía de Uso

## 📄 Descripción

Este proyecto utiliza un **script SQL nativo** (`data.sql`) para poblar la base de datos automáticamente al iniciar la aplicación. Esta es una solución más eficiente y directa que usar un seeder Java con servicios.

## ✨ Ventajas del Seeder SQL

1. **✅ Más rápido**: Inserciones directas en SQL son más eficientes
2. **✅ Sin problemas de entidades detached**: No depende de Hibernate/JPA
3. **✅ Control total**: SQL estándar que funciona en cualquier base de datos PostgreSQL
4. **✅ Fácil de mantener**: Código SQL legible y estructurado
5. **✅ Ejecución automática**: Spring Boot lo ejecuta después de crear el schema

## 📋 Datos Generados

El script `data.sql` crea:

### 1. **Departamentos** (8 registros)
- Antioquia, Cundinamarca, Valle del Cauca, Atlántico
- Santander, Bolívar, Tolima, Caldas

### 2. **Ciudades** (15 registros)
- Medellín, Envigado, Bello
- Bogotá, Soacha, Chía
- Cali, Palmira
- Barranquilla, Soledad
- Bucaramanga, Floridablanca
- Cartagena, Ibagué, Manizales

### 3. **Roles de Usuario** (3 registros)
- ADMIN - Administradores
- USER - Usuarios regulares
- MANAGER - Gerentes

### 4. **Usuarios** (19 registros)
- 2 Administradores (Carlos, Ana)
- 2 Gerentes (Luis, María)
- 15 Usuarios regulares (Juan, Pedro, Diana, etc.)

### 5. **Categorías** (10 registros)
- Electrónica
- Ropa y Moda
- Hogar y Cocina
- Deportes
- Libros
- Juguetes
- Alimentos
- Salud y Belleza
- Automotriz
- Tecnología

### 6. **Productos** (20 registros)
Productos variados con precios realistas:
- Laptop HP Pavilion 15 ($1,299,000)
- Samsung Galaxy S23 ($2,499,000)
- Audífonos Sony WH-1000XM5 ($899,000)
- Zapatillas Nike Air Zoom Pegasus ($429,000)
- LEGO Star Wars Millennium Falcon ($589,000)
- Y muchos más...

### 7. **Relaciones Producto-Categoría** (23 relaciones)
Categorización many-to-many correctamente implementada

### 8. **Tiendas** (10 registros)
- MegaStore Medellín Centro
- TechWorld Bogotá
- Fashion House Bogotá
- HomeMax Cali
- SportZone Barranquilla
- ElectroCenter Bucaramanga
- MegaStore Cartagena
- FamilyMart Ibagué
- SuperPlaza Manizales

### 9. **Inventario Tienda-Producto** (50+ registros)
Stock distribuido en todas las tiendas con:
- Cantidades realistas (50-300 unidades)
- Ubicaciones específicas (Pasillo, Estante)

### 10. **Ventas** (10 registros)
Ventas de ejemplo con totales calculados

### 11. **Productos de Venta** (30+ registros)
Detalles de cada venta con cantidades y productos

## 🚀 Configuración

### Archivo: `application.properties`

```properties
# SQL Script Configuration
# Ejecutar data.sql después de crear el schema
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```

### Explicación:
- **`spring.sql.init.mode=always`**: Ejecuta data.sql en cada inicio
- **`spring.jpa.defer-datasource-initialization=true`**: Espera a que Hibernate cree las tablas antes de ejecutar data.sql

## 🎮 Uso

### Ejecución Automática

Simplemente inicia la aplicación:

```bash
mvn spring-boot:run
```

O desde tu IDE (IntelliJ IDEA, Eclipse, VS Code)

### Verificación

La aplicación ejecutará automáticamente:
1. ✅ Hibernate crea las tablas (`create-drop`)
2. ✅ Spring ejecuta `data.sql`
3. ✅ Base de datos poblada y lista para usar

## 🔧 Personalización

### Modificar Datos

Edita el archivo `src/main/resources/data.sql`:

```sql
-- Agregar más productos
INSERT INTO product (product_name, description, price, created_at, updated_at) VALUES
('Nuevo Producto', 'Descripción del producto', 99000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Agregar más ciudades
INSERT INTO city (city_name, department_id_fk) VALUES
('Pereira', (SELECT department_id FROM department WHERE department_name = 'Risaralda'));
```

### Deshabilitar el Seeder SQL

En `application.properties`:

```properties
# Deshabilitar ejecución de data.sql
spring.sql.init.mode=never
```

### Habilitar el Seeder Java

Si prefieres usar el seeder Java con servicios:

1. **Deshabilitar data.sql**:
   ```properties
   spring.sql.init.mode=never
   ```

2. **Habilitar DatabaseSeeder.java**:
   ```java
   @Component  // Descomentar esta línea
   @RequiredArgsConstructor
   @Slf4j
   public class DatabaseSeeder implements CommandLineRunner {
   ```

## 📊 Estructura del Script SQL

El script está organizado en secciones numeradas:

```
1. DEPARTAMENTOS
2. CIUDADES
3. ROLES DE USUARIO
4. USUARIOS
5. CATEGORÍAS
6. PRODUCTOS
7. RELACIÓN PRODUCTO-CATEGORÍA
8. TIENDAS
9. INVENTARIO TIENDA-PRODUCTO
10. VENTAS
11. PRODUCTOS DE VENTAS
```

Cada sección incluye comentarios explicativos.

## 🛠️ Características del Script

### ✅ Uso de Subconsultas
```sql
INSERT INTO city (city_name, department_id_fk) VALUES
('Medellín', (SELECT department_id FROM department WHERE department_name = 'Antioquia'));
```

### ✅ Relaciones Many-to-Many
```sql
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Laptop HP Pavilion 15'),
 (SELECT category_id FROM category WHERE category_name = 'Electrónica'));
```

### ✅ Timestamps Automáticos
```sql
INSERT INTO users (..., created_at) VALUES
(..., CURRENT_TIMESTAMP);
```

## 🔍 Troubleshooting

### Error: "data.sql no se ejecuta"

**Solución**: Verifica que tengas:
```properties
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```

### Error: "Constraint violation"

**Causa**: Las tablas tienen relaciones FK estrictas

**Solución**: El script respeta el orden de dependencias. No modifiques el orden sin considerar las relaciones.

### Error: "Datos duplicados"

**Causa**: Con `create-drop`, la base de datos se recrea en cada inicio

**Solución**: Esto es normal. Si quieres persistir datos entre ejecuciones, cambia a:
```properties
spring.jpa.hibernate.ddl-auto=update
```

## 🎯 Mejores Prácticas

### Desarrollo Local
✅ Usa `create-drop` + `data.sql`  
✅ Reinicia frecuentemente con datos frescos

### Testing
✅ Usa `data.sql` para datos de prueba consistentes  
✅ Todos los tests tienen los mismos datos iniciales

### Staging
⚠️ Considera usar datos más realistas  
⚠️ O datos anonimizados de producción

### Producción
❌ **NUNCA uses `create-drop`**  
❌ **NUNCA habilites `spring.sql.init.mode=always`**  
✅ Usa migraciones (Flyway/Liquibase)

## 📝 Comparación: SQL vs Java Seeder

| Característica | SQL Seeder | Java Seeder |
|----------------|-----------|-------------|
| Velocidad | ⚡ Muy rápido | 🐌 Más lento |
| Problemas detached | ✅ No aplica | ❌ Puede ocurrir |
| Mantenimiento | ✅ SQL estándar | ⚠️ Depende de servicios |
| Lógica de negocio | ❌ No se ejecuta | ✅ Se ejecuta |
| Testing | ✅ Excelente | ⚠️ Bueno |
| Flexibilidad | ⚠️ Limitado | ✅ Total |

## 🌟 Recomendación

Para **desarrollo y testing**: Usa el **SQL Seeder** (actual)
- Más rápido
- Sin complicaciones
- Datos consistentes

Para **casos especiales** (validaciones complejas, datos dinámicos): Usa el **Java Seeder**
- Descomenta `@Component`
- Deshabilita `data.sql`

## 📚 Referencias

- [Spring Boot Database Initialization](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization)
- [PostgreSQL INSERT Documentation](https://www.postgresql.org/docs/current/sql-insert.html)

## 👥 Autor

**Commercial Management System Team**  
Version: 1.0  
Fecha: 2025-11-22

