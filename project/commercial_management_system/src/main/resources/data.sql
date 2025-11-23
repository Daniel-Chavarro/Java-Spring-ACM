-- =====================================================
-- Database Seeder SQL Script
-- Commercial Management System
-- Author: Commercial Management System Team
-- Version: 1.0
-- Date: 2025-11-22
-- =====================================================
-- Este script puebla la base de datos con datos de prueba
-- Se ejecuta automáticamente al iniciar la aplicación
-- =====================================================

-- =====================================================
-- 1. DEPARTAMENTOS
-- =====================================================
INSERT INTO department (department_name) VALUES
('Antioquia'),
('Cundinamarca'),
('Valle del Cauca'),
('Atlántico'),
('Santander'),
('Bolívar'),
('Tolima'),
('Caldas');

-- =====================================================
-- 2. CIUDADES
-- =====================================================
-- Antioquia
INSERT INTO city (city_name, department_id_fk) VALUES
('Medellín', (SELECT department_id FROM department WHERE department_name = 'Antioquia')),
('Envigado', (SELECT department_id FROM department WHERE department_name = 'Antioquia')),
('Bello', (SELECT department_id FROM department WHERE department_name = 'Antioquia'));

-- Cundinamarca
INSERT INTO city (city_name, department_id_fk) VALUES
('Bogotá', (SELECT department_id FROM department WHERE department_name = 'Cundinamarca')),
('Soacha', (SELECT department_id FROM department WHERE department_name = 'Cundinamarca')),
('Chía', (SELECT department_id FROM department WHERE department_name = 'Cundinamarca'));

-- Valle del Cauca
INSERT INTO city (city_name, department_id_fk) VALUES
('Cali', (SELECT department_id FROM department WHERE department_name = 'Valle del Cauca')),
('Palmira', (SELECT department_id FROM department WHERE department_name = 'Valle del Cauca'));

-- Atlántico
INSERT INTO city (city_name, department_id_fk) VALUES
('Barranquilla', (SELECT department_id FROM department WHERE department_name = 'Atlántico')),
('Soledad', (SELECT department_id FROM department WHERE department_name = 'Atlántico'));

-- Santander
INSERT INTO city (city_name, department_id_fk) VALUES
('Bucaramanga', (SELECT department_id FROM department WHERE department_name = 'Santander')),
('Floridablanca', (SELECT department_id FROM department WHERE department_name = 'Santander'));

-- Bolívar
INSERT INTO city (city_name, department_id_fk) VALUES
('Cartagena', (SELECT department_id FROM department WHERE department_name = 'Bolívar'));

-- Tolima
INSERT INTO city (city_name, department_id_fk) VALUES
('Ibagué', (SELECT department_id FROM department WHERE department_name = 'Tolima'));

-- Caldas
INSERT INTO city (city_name, department_id_fk) VALUES
('Manizales', (SELECT department_id FROM department WHERE department_name = 'Caldas'));

-- =====================================================
-- 3. ROLES DE USUARIO
-- =====================================================
INSERT INTO user_role (role) VALUES
('ADMIN'),
('USER'),
('MANAGER');

-- =====================================================
-- 4. USUARIOS
-- =====================================================
-- Administradores
INSERT INTO users (user_id, first_name, last_name, username, email, password, phone, role_id_fk, city_id_fk, created_at) VALUES
(gen_random_uuid(), 'Carlos', 'Rodríguez', 'crodriguez', 'carlos.rodriguez@example.com', '$2a$10$encrypted.password.hash', '3001234567',
 (SELECT user_role_id FROM user_role WHERE role = 'ADMIN'),
 (SELECT city_id FROM city WHERE city_name = 'Medellín'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Ana', 'Martínez', 'amartinez', 'ana.martinez@example.com', '$2a$10$encrypted.password.hash', '3009876543',
 (SELECT user_role_id FROM user_role WHERE role = 'ADMIN'),
 (SELECT city_id FROM city WHERE city_name = 'Bogotá'), CURRENT_TIMESTAMP);

-- Gerentes
INSERT INTO users (user_id, first_name, last_name, username, email, password, phone, role_id_fk, city_id_fk, created_at) VALUES
(gen_random_uuid(), 'Luis', 'González', 'lgonzalez', 'luis.gonzalez@example.com', '$2a$10$encrypted.password.hash', '3101234567',
 (SELECT user_role_id FROM user_role WHERE role = 'MANAGER'),
 (SELECT city_id FROM city WHERE city_name = 'Envigado'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'María', 'López', 'mlopez', 'maria.lopez@example.com', '$2a$10$encrypted.password.hash', '3209876543',
 (SELECT user_role_id FROM user_role WHERE role = 'MANAGER'),
 (SELECT city_id FROM city WHERE city_name = 'Cali'), CURRENT_TIMESTAMP);

-- Usuarios regulares
INSERT INTO users (user_id, first_name, last_name, username, email, password, phone, role_id_fk, city_id_fk, created_at) VALUES
(gen_random_uuid(), 'Juan', 'García', 'juangarcia0', 'juangarcia0@example.com', '$2a$10$encrypted.password.hash', '3012345670',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Medellín'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Pedro', 'Ramírez', 'pedroramirez1', 'pedroramirez1@example.com', '$2a$10$encrypted.password.hash', '3012345671',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Bogotá'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Diana', 'Torres', 'dianatorres2', 'dianatorres2@example.com', '$2a$10$encrypted.password.hash', '3012345672',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Cali'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Laura', 'Flores', 'lauraflores3', 'lauraflores3@example.com', '$2a$10$encrypted.password.hash', '3012345673',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Barranquilla'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Miguel', 'Herrera', 'miguelherrera4', 'miguelherrera4@example.com', '$2a$10$encrypted.password.hash', '3012345674',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Bucaramanga'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Sofia', 'Castro', 'sofiacastro5', 'sofiacastro5@example.com', '$2a$10$encrypted.password.hash', '3012345675',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Cartagena'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Andrés', 'Vargas', 'andresvargas6', 'andresvargas6@example.com', '$2a$10$encrypted.password.hash', '3012345676',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Ibagué'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Carolina', 'Rojas', 'carolinarojas7', 'carolinarojas7@example.com', '$2a$10$encrypted.password.hash', '3012345677',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Manizales'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'David', 'Moreno', 'davidmoreno8', 'davidmoreno8@example.com', '$2a$10$encrypted.password.hash', '3012345678',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Bello'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Paula', 'Jiménez', 'paulajimenez9', 'paulajimenez9@example.com', '$2a$10$encrypted.password.hash', '3012345679',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Soacha'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Roberto', 'Sánchez', 'robertosanchez10', 'robertosanchez10@example.com', '$2a$10$encrypted.password.hash', '3012345680',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Chía'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Valentina', 'Mendoza', 'valentinamendoza11', 'valentinamendoza11@example.com', '$2a$10$encrypted.password.hash', '3012345681',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Palmira'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Santiago', 'Cruz', 'santiagocruz12', 'santiagocruz12@example.com', '$2a$10$encrypted.password.hash', '3012345682',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Soledad'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Camila', 'Ortiz', 'camilaortiz13', 'camilaortiz13@example.com', '$2a$10$encrypted.password.hash', '3012345683',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Floridablanca'), CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Felipe', 'Ruiz', 'feliperuiz14', 'feliperuiz14@example.com', '$2a$10$encrypted.password.hash', '3012345684',
 (SELECT user_role_id FROM user_role WHERE role = 'USER'),
 (SELECT city_id FROM city WHERE city_name = 'Medellín'), CURRENT_TIMESTAMP);

-- =====================================================
-- 5. CATEGORÍAS
-- =====================================================
INSERT INTO category (category_name) VALUES
('Electrónica'),
('Ropa y Moda'),
('Hogar y Cocina'),
('Deportes'),
('Libros'),
('Juguetes'),
('Alimentos'),
('Salud y Belleza'),
('Automotriz'),
('Tecnología');

-- =====================================================
-- 6. PRODUCTOS
-- =====================================================
INSERT INTO product (product_id, product_name, description, price, created_at, updated_at) VALUES
-- Electrónica y Tecnología
(gen_random_uuid(), 'Laptop HP Pavilion 15', 'Laptop con procesador Intel Core i5, 8GB RAM, 256GB SSD', 1299000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Samsung Galaxy S23', 'Smartphone 5G con pantalla AMOLED de 6.1 pulgadas, 128GB', 2499000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Audífonos Sony WH-1000XM5', 'Audífonos inalámbricos con cancelación de ruido', 899000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- Ropa
(gen_random_uuid(), 'Camiseta Polo Lacoste', 'Camiseta polo de algodón 100%, varios colores disponibles', 189000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Jeans Levi''s 501', 'Jeans clásicos de corte recto, mezclilla premium', 249000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- Hogar
(gen_random_uuid(), 'Licuadora Oster 10 velocidades', 'Licuadora de alta potencia con vaso de vidrio', 179000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Juego de Sartenes Antiadherentes', 'Set de 3 sartenes de diferentes tamaños', 149000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- Deportes
(gen_random_uuid(), 'Balón de Fútbol Adidas', 'Balón oficial tamaño 5, ideal para competencias', 89000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Zapatillas Nike Air Zoom Pegasus', 'Zapatillas para running con tecnología de amortiguación', 429000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Mancuernas Ajustables 20kg', 'Set de mancuernas con pesos ajustables', 299000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- Libros
(gen_random_uuid(), 'Cien Años de Soledad - Gabriel García Márquez', 'Novela clásica de la literatura latinoamericana', 49000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'El Alquimista - Paulo Coelho', 'Bestseller internacional sobre la búsqueda de los sueños', 42000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- Juguetes
(gen_random_uuid(), 'LEGO Star Wars Millennium Falcon', 'Set de construcción con 1351 piezas', 589000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Muñeca Barbie Fashionista', 'Muñeca con accesorios y cambios de ropa', 79000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- Alimentos
(gen_random_uuid(), 'Café Juan Valdez Colina 500g', 'Café colombiano 100% premium molido', 32000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Aceite de Oliva Extra Virgen 500ml', 'Aceite de oliva importado de España', 45000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- Salud y Belleza
(gen_random_uuid(), 'Crema Facial L''Oréal Anti-Edad', 'Crema hidratante con retinol para todo tipo de piel', 89000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Shampoo Pantene Pro-V 400ml', 'Shampoo reparador para cabello dañado', 28000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- Automotriz
(gen_random_uuid(), 'Aceite Motor Mobil 1 Sintético 4L', 'Aceite sintético de alta performance', 129000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Kit de Herramientas Básicas', 'Set de 50 piezas para mantenimiento automotriz', 199000.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =====================================================
-- 7. RELACIÓN PRODUCTO-CATEGORÍA (Many to Many)
-- =====================================================
-- Laptop - Electrónica y Tecnología
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Laptop HP Pavilion 15'),
 (SELECT category_id FROM category WHERE category_name = 'Electrónica')),
((SELECT product_id FROM product WHERE product_name = 'Laptop HP Pavilion 15'),
 (SELECT category_id FROM category WHERE category_name = 'Tecnología'));

-- Samsung - Electrónica y Tecnología
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Samsung Galaxy S23'),
 (SELECT category_id FROM category WHERE category_name = 'Electrónica')),
((SELECT product_id FROM product WHERE product_name = 'Samsung Galaxy S23'),
 (SELECT category_id FROM category WHERE category_name = 'Tecnología'));

-- Audífonos - Electrónica
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Audífonos Sony WH-1000XM5'),
 (SELECT category_id FROM category WHERE category_name = 'Electrónica'));

-- Polo - Ropa
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Camiseta Polo Lacoste'),
 (SELECT category_id FROM category WHERE category_name = 'Ropa y Moda'));

-- Jeans - Ropa
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Jeans Levi''s 501'),
 (SELECT category_id FROM category WHERE category_name = 'Ropa y Moda'));

-- Licuadora - Hogar
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Licuadora Oster 10 velocidades'),
 (SELECT category_id FROM category WHERE category_name = 'Hogar y Cocina'));

-- Sartenes - Hogar
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Juego de Sartenes Antiadherentes'),
 (SELECT category_id FROM category WHERE category_name = 'Hogar y Cocina'));

-- Balón - Deportes
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Balón de Fútbol Adidas'),
 (SELECT category_id FROM category WHERE category_name = 'Deportes'));

-- Zapatillas - Deportes y Ropa
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Zapatillas Nike Air Zoom Pegasus'),
 (SELECT category_id FROM category WHERE category_name = 'Deportes')),
((SELECT product_id FROM product WHERE product_name = 'Zapatillas Nike Air Zoom Pegasus'),
 (SELECT category_id FROM category WHERE category_name = 'Ropa y Moda'));

-- Mancuernas - Deportes
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Mancuernas Ajustables 20kg'),
 (SELECT category_id FROM category WHERE category_name = 'Deportes'));

-- Cien Años de Soledad - Libros
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Cien Años de Soledad - Gabriel García Márquez'),
 (SELECT category_id FROM category WHERE category_name = 'Libros'));

-- El Alquimista - Libros
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'El Alquimista - Paulo Coelho'),
 (SELECT category_id FROM category WHERE category_name = 'Libros'));

-- LEGO - Juguetes
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'LEGO Star Wars Millennium Falcon'),
 (SELECT category_id FROM category WHERE category_name = 'Juguetes'));

-- Barbie - Juguetes
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Muñeca Barbie Fashionista'),
 (SELECT category_id FROM category WHERE category_name = 'Juguetes'));

-- Café - Alimentos
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Café Juan Valdez Colina 500g'),
 (SELECT category_id FROM category WHERE category_name = 'Alimentos'));

-- Aceite de Oliva - Alimentos
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Aceite de Oliva Extra Virgen 500ml'),
 (SELECT category_id FROM category WHERE category_name = 'Alimentos'));

-- Crema - Salud y Belleza
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Crema Facial L''Oréal Anti-Edad'),
 (SELECT category_id FROM category WHERE category_name = 'Salud y Belleza'));

-- Shampoo - Salud y Belleza
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Shampoo Pantene Pro-V 400ml'),
 (SELECT category_id FROM category WHERE category_name = 'Salud y Belleza'));

-- Aceite Motor - Automotriz
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Aceite Motor Mobil 1 Sintético 4L'),
 (SELECT category_id FROM category WHERE category_name = 'Automotriz'));

-- Kit Herramientas - Automotriz
INSERT INTO product_category (product_id_fk, category_id_fk) VALUES
((SELECT product_id FROM product WHERE product_name = 'Kit de Herramientas Básicas'),
 (SELECT category_id FROM category WHERE category_name = 'Automotriz'));

-- =====================================================
-- 8. TIENDAS
-- =====================================================
INSERT INTO store (store_id, store_name, city_id_fk) VALUES
(gen_random_uuid(), 'MegaStore Medellín Centro', (SELECT city_id FROM city WHERE city_name = 'Medellín')),
(gen_random_uuid(), 'MegaStore Envigado', (SELECT city_id FROM city WHERE city_name = 'Envigado')),
(gen_random_uuid(), 'TechWorld Bogotá', (SELECT city_id FROM city WHERE city_name = 'Bogotá')),
(gen_random_uuid(), 'Fashion House Bogotá', (SELECT city_id FROM city WHERE city_name = 'Bogotá')),
(gen_random_uuid(), 'HomeMax Cali', (SELECT city_id FROM city WHERE city_name = 'Cali')),
(gen_random_uuid(), 'SportZone Barranquilla', (SELECT city_id FROM city WHERE city_name = 'Barranquilla')),
(gen_random_uuid(), 'ElectroCenter Bucaramanga', (SELECT city_id FROM city WHERE city_name = 'Bucaramanga')),
(gen_random_uuid(), 'MegaStore Cartagena', (SELECT city_id FROM city WHERE city_name = 'Cartagena')),
(gen_random_uuid(), 'FamilyMart Ibagué', (SELECT city_id FROM city WHERE city_name = 'Ibagué')),
(gen_random_uuid(), 'SuperPlaza Manizales', (SELECT city_id FROM city WHERE city_name = 'Manizales'));

-- =====================================================
-- 9. INVENTARIO TIENDA-PRODUCTO
-- =====================================================
-- MegaStore Medellín Centro (productos variados)
INSERT INTO store_product (stock, address, store_id_fk, product_id_fk) VALUES
(150, 'Pasillo A, Estante 5', (SELECT store_id FROM store WHERE store_name = 'MegaStore Medellín Centro'),
 (SELECT product_id FROM product WHERE product_name = 'Laptop HP Pavilion 15')),
(200, 'Pasillo A, Estante 8', (SELECT store_id FROM store WHERE store_name = 'MegaStore Medellín Centro'),
 (SELECT product_id FROM product WHERE product_name = 'Samsung Galaxy S23')),
(120, 'Pasillo B, Estante 3', (SELECT store_id FROM store WHERE store_name = 'MegaStore Medellín Centro'),
 (SELECT product_id FROM product WHERE product_name = 'Camiseta Polo Lacoste')),
(180, 'Pasillo B, Estante 7', (SELECT store_id FROM store WHERE store_name = 'MegaStore Medellín Centro'),
 (SELECT product_id FROM product WHERE product_name = 'Jeans Levi''s 501')),
(90, 'Pasillo C, Estante 2', (SELECT store_id FROM store WHERE store_name = 'MegaStore Medellín Centro'),
 (SELECT product_id FROM product WHERE product_name = 'Licuadora Oster 10 velocidades')),
(250, 'Pasillo D, Estante 10', (SELECT store_id FROM store WHERE store_name = 'MegaStore Medellín Centro'),
 (SELECT product_id FROM product WHERE product_name = 'Balón de Fútbol Adidas')),
(140, 'Pasillo E, Estante 4', (SELECT store_id FROM store WHERE store_name = 'MegaStore Medellín Centro'),
 (SELECT product_id FROM product WHERE product_name = 'LEGO Star Wars Millennium Falcon')),
(300, 'Pasillo F, Estante 1', (SELECT store_id FROM store WHERE store_name = 'MegaStore Medellín Centro'),
 (SELECT product_id FROM product WHERE product_name = 'Café Juan Valdez Colina 500g')),
(170, 'Pasillo G, Estante 6', (SELECT store_id FROM store WHERE store_name = 'MegaStore Medellín Centro'),
 (SELECT product_id FROM product WHERE product_name = 'Crema Facial L''Oréal Anti-Edad')),
(110, 'Pasillo H, Estante 9', (SELECT store_id FROM store WHERE store_name = 'MegaStore Medellín Centro'),
 (SELECT product_id FROM product WHERE product_name = 'Kit de Herramientas Básicas'));

-- TechWorld Bogotá (enfoque en tecnología)
INSERT INTO store_product (stock, address, store_id_fk, product_id_fk) VALUES
(200, 'Zona Tech A1', (SELECT store_id FROM store WHERE store_name = 'TechWorld Bogotá'),
 (SELECT product_id FROM product WHERE product_name = 'Laptop HP Pavilion 15')),
(250, 'Zona Tech A2', (SELECT store_id FROM store WHERE store_name = 'TechWorld Bogotá'),
 (SELECT product_id FROM product WHERE product_name = 'Samsung Galaxy S23')),
(180, 'Zona Tech B1', (SELECT store_id FROM store WHERE store_name = 'TechWorld Bogotá'),
 (SELECT product_id FROM product WHERE product_name = 'Audífonos Sony WH-1000XM5')),
(150, 'Zona Accesorios C1', (SELECT store_id FROM store WHERE store_name = 'TechWorld Bogotá'),
 (SELECT product_id FROM product WHERE product_name = 'Kit de Herramientas Básicas'));

-- Fashion House Bogotá (enfoque en moda)
INSERT INTO store_product (stock, address, store_id_fk, product_id_fk) VALUES
(300, 'Sección Caballeros A', (SELECT store_id FROM store WHERE store_name = 'Fashion House Bogotá'),
 (SELECT product_id FROM product WHERE product_name = 'Camiseta Polo Lacoste')),
(280, 'Sección Caballeros B', (SELECT store_id FROM store WHERE store_name = 'Fashion House Bogotá'),
 (SELECT product_id FROM product WHERE product_name = 'Jeans Levi''s 501')),
(200, 'Sección Deportes', (SELECT store_id FROM store WHERE store_name = 'Fashion House Bogotá'),
 (SELECT product_id FROM product WHERE product_name = 'Zapatillas Nike Air Zoom Pegasus')),
(150, 'Sección Belleza', (SELECT store_id FROM store WHERE store_name = 'Fashion House Bogotá'),
 (SELECT product_id FROM product WHERE product_name = 'Crema Facial L''Oréal Anti-Edad')),
(220, 'Sección Belleza', (SELECT store_id FROM store WHERE store_name = 'Fashion House Bogotá'),
 (SELECT product_id FROM product WHERE product_name = 'Shampoo Pantene Pro-V 400ml'));

-- HomeMax Cali (enfoque en hogar)
INSERT INTO store_product (stock, address, store_id_fk, product_id_fk) VALUES
(160, 'Pasillo Cocina 1', (SELECT store_id FROM store WHERE store_name = 'HomeMax Cali'),
 (SELECT product_id FROM product WHERE product_name = 'Licuadora Oster 10 velocidades')),
(190, 'Pasillo Cocina 2', (SELECT store_id FROM store WHERE store_name = 'HomeMax Cali'),
 (SELECT product_id FROM product WHERE product_name = 'Juego de Sartenes Antiadherentes')),
(250, 'Pasillo Alimentos', (SELECT store_id FROM store WHERE store_name = 'HomeMax Cali'),
 (SELECT product_id FROM product WHERE product_name = 'Café Juan Valdez Colina 500g')),
(200, 'Pasillo Alimentos', (SELECT store_id FROM store WHERE store_name = 'HomeMax Cali'),
 (SELECT product_id FROM product WHERE product_name = 'Aceite de Oliva Extra Virgen 500ml'));

-- SportZone Barranquilla (enfoque en deportes)
INSERT INTO store_product (stock, address, store_id_fk, product_id_fk) VALUES
(300, 'Zona Fútbol', (SELECT store_id FROM store WHERE store_name = 'SportZone Barranquilla'),
 (SELECT product_id FROM product WHERE product_name = 'Balón de Fútbol Adidas')),
(180, 'Zona Running', (SELECT store_id FROM store WHERE store_name = 'SportZone Barranquilla'),
 (SELECT product_id FROM product WHERE product_name = 'Zapatillas Nike Air Zoom Pegasus')),
(150, 'Zona Gym', (SELECT store_id FROM store WHERE store_name = 'SportZone Barranquilla'),
 (SELECT product_id FROM product WHERE product_name = 'Mancuernas Ajustables 20kg'));

-- ElectroCenter Bucaramanga (enfoque en electrónica)
INSERT INTO store_product (stock, address, store_id_fk, product_id_fk) VALUES
(170, 'Zona Computadores', (SELECT store_id FROM store WHERE store_name = 'ElectroCenter Bucaramanga'),
 (SELECT product_id FROM product WHERE product_name = 'Laptop HP Pavilion 15')),
(220, 'Zona Smartphones', (SELECT store_id FROM store WHERE store_name = 'ElectroCenter Bucaramanga'),
 (SELECT product_id FROM product WHERE product_name = 'Samsung Galaxy S23')),
(160, 'Zona Audio', (SELECT store_id FROM store WHERE store_name = 'ElectroCenter Bucaramanga'),
 (SELECT product_id FROM product WHERE product_name = 'Audífonos Sony WH-1000XM5')),
(140, 'Zona Hogar', (SELECT store_id FROM store WHERE store_name = 'ElectroCenter Bucaramanga'),
 (SELECT product_id FROM product WHERE product_name = 'Licuadora Oster 10 velocidades'));

-- MegaStore Cartagena (variedad)
INSERT INTO store_product (stock, address, store_id_fk, product_id_fk) VALUES
(130, 'Pasillo 1-A', (SELECT store_id FROM store WHERE store_name = 'MegaStore Cartagena'),
 (SELECT product_id FROM product WHERE product_name = 'Samsung Galaxy S23')),
(170, 'Pasillo 2-B', (SELECT store_id FROM store WHERE store_name = 'MegaStore Cartagena'),
 (SELECT product_id FROM product WHERE product_name = 'Jeans Levi''s 501')),
(200, 'Pasillo 3-C', (SELECT store_id FROM store WHERE store_name = 'MegaStore Cartagena'),
 (SELECT product_id FROM product WHERE product_name = 'Balón de Fútbol Adidas')),
(150, 'Pasillo 4-D', (SELECT store_id FROM store WHERE store_name = 'MegaStore Cartagena'),
 (SELECT product_id FROM product WHERE product_name = 'Muñeca Barbie Fashionista')),
(280, 'Pasillo 5-E', (SELECT store_id FROM store WHERE store_name = 'MegaStore Cartagena'),
 (SELECT product_id FROM product WHERE product_name = 'Café Juan Valdez Colina 500g'));

-- FamilyMart Ibagué (variedad familiar)
INSERT INTO store_product (stock, address, store_id_fk, product_id_fk) VALUES
(180, 'Sección Tecnología', (SELECT store_id FROM store WHERE store_name = 'FamilyMart Ibagué'),
 (SELECT product_id FROM product WHERE product_name = 'Audífonos Sony WH-1000XM5')),
(160, 'Sección Hogar', (SELECT store_id FROM store WHERE store_name = 'FamilyMart Ibagué'),
 (SELECT product_id FROM product WHERE product_name = 'Juego de Sartenes Antiadherentes')),
(200, 'Sección Juguetes', (SELECT store_id FROM store WHERE store_name = 'FamilyMart Ibagué'),
 (SELECT product_id FROM product WHERE product_name = 'LEGO Star Wars Millennium Falcon')),
(180, 'Sección Juguetes', (SELECT store_id FROM store WHERE store_name = 'FamilyMart Ibagué'),
 (SELECT product_id FROM product WHERE product_name = 'Muñeca Barbie Fashionista')),
(220, 'Sección Libros', (SELECT store_id FROM store WHERE store_name = 'FamilyMart Ibagué'),
 (SELECT product_id FROM product WHERE product_name = 'Cien Años de Soledad - Gabriel García Márquez'));

-- SuperPlaza Manizales (variedad general)
INSERT INTO store_product (stock, address, store_id_fk, product_id_fk) VALUES
(140, 'Nivel 1, Zona A', (SELECT store_id FROM store WHERE store_name = 'SuperPlaza Manizales'),
 (SELECT product_id FROM product WHERE product_name = 'Laptop HP Pavilion 15')),
(190, 'Nivel 1, Zona B', (SELECT store_id FROM store WHERE store_name = 'SuperPlaza Manizales'),
 (SELECT product_id FROM product WHERE product_name = 'Camiseta Polo Lacoste')),
(170, 'Nivel 2, Zona C', (SELECT store_id FROM store WHERE store_name = 'SuperPlaza Manizales'),
 (SELECT product_id FROM product WHERE product_name = 'Mancuernas Ajustables 20kg')),
(210, 'Nivel 2, Zona D', (SELECT store_id FROM store WHERE store_name = 'SuperPlaza Manizales'),
 (SELECT product_id FROM product WHERE product_name = 'El Alquimista - Paulo Coelho')),
(160, 'Nivel 3, Zona E', (SELECT store_id FROM store WHERE store_name = 'SuperPlaza Manizales'),
 (SELECT product_id FROM product WHERE product_name = 'Aceite Motor Mobil 1 Sintético 4L'));

-- =====================================================
-- 10. VENTAS
-- =====================================================
-- Crear algunas ventas de ejemplo
INSERT INTO sale (sale_id, sale_date, total_amount, user_id_fk) VALUES
(gen_random_uuid(), CURRENT_TIMESTAMP, 3198000, (SELECT user_id FROM users WHERE username = 'juangarcia0')),
(gen_random_uuid(), CURRENT_TIMESTAMP, 1348000, (SELECT user_id FROM users WHERE username = 'pedroramirez1')),
(gen_random_uuid(), CURRENT_TIMESTAMP, 2827000, (SELECT user_id FROM users WHERE username = 'dianatorres2')),
(gen_random_uuid(), CURRENT_TIMESTAMP, 538000, (SELECT user_id FROM users WHERE username = 'lauraflores3')),
(gen_random_uuid(), CURRENT_TIMESTAMP, 1477000, (SELECT user_id FROM users WHERE username = 'miguelherrera4')),
(gen_random_uuid(), CURRENT_TIMESTAMP, 728000, (SELECT user_id FROM users WHERE username = 'sofiacastro5')),
(gen_random_uuid(), CURRENT_TIMESTAMP, 4296000, (SELECT user_id FROM users WHERE username = 'andresvargas6')),
(gen_random_uuid(), CURRENT_TIMESTAMP, 378000, (SELECT user_id FROM users WHERE username = 'carolinarojas7')),
(gen_random_uuid(), CURRENT_TIMESTAMP, 897000, (SELECT user_id FROM users WHERE username = 'davidmoreno8')),
(gen_random_uuid(), CURRENT_TIMESTAMP, 2648000, (SELECT user_id FROM users WHERE username = 'paulajimenez9'));

-- =====================================================
-- 11. PRODUCTOS DE VENTAS
-- =====================================================
-- Venta 1: Juan García (Laptop + Samsung)
INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'juangarcia0'
  AND p.product_name = 'Laptop HP Pavilion 15';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'juangarcia0'
  AND p.product_name = 'Audífonos Sony WH-1000XM5';

-- Venta 2: Pedro Ramírez (Ropa)
INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 2, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'pedroramirez1'
  AND p.product_name = 'Camiseta Polo Lacoste';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 3, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'pedroramirez1'
  AND p.product_name = 'Jeans Levi''s 501';

-- Venta 3: Diana Torres (Samsung + Zapatillas)
INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'dianatorres2'
  AND p.product_name = 'Samsung Galaxy S23';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'dianatorres2'
  AND p.product_name = 'Licuadora Oster 10 velocidades';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'dianatorres2'
  AND p.product_name = 'Juego de Sartenes Antiadherentes';

-- Venta 4: Laura Flores (Deportes)
INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 3, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'lauraflores3'
  AND p.product_name = 'Balón de Fútbol Adidas';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'lauraflores3'
  AND p.product_name = 'Mancuernas Ajustables 20kg';

-- Venta 5: Miguel Herrera (Licuadora + Sartenes + Café)
INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 2, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'miguelherrera4'
  AND p.product_name = 'Licuadora Oster 10 velocidades';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 3, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'miguelherrera4'
  AND p.product_name = 'Juego de Sartenes Antiadherentes';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 5, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'miguelherrera4'
  AND p.product_name = 'Café Juan Valdez Colina 500g';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 2, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'miguelherrera4'
  AND p.product_name = 'Zapatillas Nike Air Zoom Pegasus';

-- Venta 6: Sofia Castro (Juguetes)
INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'sofiacastro5'
  AND p.product_name = 'LEGO Star Wars Millennium Falcon';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 2, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'sofiacastro5'
  AND p.product_name = 'Muñeca Barbie Fashionista';

-- Venta 7: Andrés Vargas (Tech Premium)
INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'andresvargas6'
  AND p.product_name = 'Laptop HP Pavilion 15';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'andresvargas6'
  AND p.product_name = 'Samsung Galaxy S23';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'andresvargas6'
  AND p.product_name = 'Audífonos Sony WH-1000XM5';

-- Venta 8: Carolina Rojas (Libros + Alimentos)
INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 2, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'carolinarojas7'
  AND p.product_name = 'Cien Años de Soledad - Gabriel García Márquez';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 3, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'carolinarojas7'
  AND p.product_name = 'El Alquimista - Paulo Coelho';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 4, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'carolinarojas7'
  AND p.product_name = 'Café Juan Valdez Colina 500g';

-- Venta 9: David Moreno (Belleza + Deportes)
INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 3, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'davidmoreno8'
  AND p.product_name = 'Crema Facial L''Oréal Anti-Edad';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 5, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'davidmoreno8'
  AND p.product_name = 'Shampoo Pantene Pro-V 400ml';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 2, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'davidmoreno8'
  AND p.product_name = 'Balón de Fútbol Adidas';

-- Venta 10: Paula Jiménez (Samsung + Zapatillas + Kit)
INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'paulajimenez9'
  AND p.product_name = 'Samsung Galaxy S23';

INSERT INTO sale_product (quantity, sale_id_fk, product_id_fk)
SELECT 1, s.sale_id, p.product_id
FROM sale s, product p, users u
WHERE s.user_id_fk = u.user_id
  AND u.username = 'paulajimenez9'
  AND p.product_name = 'Aceite Motor Mobil 1 Sintético 4L';

-- =====================================================
-- FIN DEL SCRIPT
-- =====================================================

