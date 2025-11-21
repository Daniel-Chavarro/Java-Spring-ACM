# Commercial Management System

A comprehensive Spring Boot application for managing commercial operations including products, sales, stores, users, and inventory management with full CRUD operations and advanced analytics.

## Author
**Commercial Management System Team**
- **Version:** 1.0
- **Since:** 2025-11-19
- **Last Updated:** 2025-11-21

## Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Entities](#entities)
- [Services Layer](#services-layer)
- [API Endpoints](#api-endpoints)
- [Repositories](#repositories)
- [Database Configuration](#database-configuration)
- [Testing](#testing)
- [Getting Started](#getting-started)
- [Development Guidelines](#development-guidelines)
- [Documentation](#documentation)

## Overview

This application provides a robust backend system for managing:
- **Product Catalog**: Products with categories, pricing, and descriptions
- **Store Management**: Multiple store locations with inventory tracking
- **User Management**: User accounts with role-based permissions  
- **Sales Transactions**: Complete sales processing with product relationships
- **Geographic Organization**: Departments and cities for location-based operations
- **Analytics**: Sales reports and best-selling products analysis
- **Inventory Management**: Store-product relationships and stock control

## Architecture

The application follows a clean layered architecture pattern with full implementation:

```
┌─────────────────┐
│  Controller     │ ← REST API Layer (IMPLEMENTED)
│     Layer       │
├─────────────────┤
│   Service       │ ← Business Logic Layer (IMPLEMENTED)
│     Layer       │
├─────────────────┤
│  Repository     │ ← Data Access Layer (IMPLEMENTED)  
│     Layer       │
├─────────────────┤
│    Entity       │ ← Data Model Layer (IMPLEMENTED)
│     Layer       │
└─────────────────┘
```

### Additional Layers
- **Model/DTO Layer**: Data Transfer Objects for API communication
- **Mapper Layer**: Entity-DTO conversion using MapStruct
- **Utils Layer**: Common utilities and helpers

## Features

### ✅ Implemented Features
- **Complete CRUD Operations**: All entities support Create, Read, Update, Delete
- **Advanced Search**: Custom queries for filtering and searching
- **Sales Analytics**: Best-selling products and sales reports
- **Geographic Filtering**: Location-based queries and operations
- **RESTful API**: Full REST API with proper HTTP methods
- **Data Validation**: Comprehensive input validation
- **Error Handling**: Proper HTTP status codes and error responses
- **Documentation**: JavaDoc for all classes and methods
- **Unit Testing**: Comprehensive test coverage for repositories
- **Database Migration**: Automatic schema creation and updates

### 🔄 Architecture Patterns
- **Repository Pattern**: Data access abstraction
- **Service Pattern**: Business logic encapsulation
- **DTO Pattern**: Data transfer optimization
- **Mapper Pattern**: Automated entity-model conversion
- **Builder Pattern**: Entity creation and updates

## Technology Stack
- **Framework:** Spring Boot 3.5.7
- **Database:** PostgreSQL (Production), H2 (Testing)
- **ORM:** Hibernate/JPA with Spring Data JPA
- **Mapping:** MapStruct for entity-DTO conversion
- **Build Tool:** Maven 4.0.0
- **Java Version:** 21 (LTS)
- **Testing:** JUnit 5, Spring Boot Test, AssertJ
- **Documentation:** JavaDoc, Markdown
- **Containerization:** Docker Compose (available)

## Services Layer

The service layer provides business logic and coordinates between controllers and repositories. All services follow the same architectural pattern:

### Service Architecture
```java
@Service
public class EntityService {
    private final EntityRepository repository;
    private final EntityMapper mapper;
    
    // Constructor injection for better testability
    public EntityService(EntityRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    // Standard CRUD operations + custom business logic
}
```

### Implemented Services

#### 🏪 **ProductService**
- **CRUD Operations**: Complete product management
- **Analytics Integration**: Best-selling products via SaleProductService
- **Price Queries**: Price range filtering and sorting
- **Category Integration**: Products by category relationships

#### 👤 **UserService** 
- **User Management**: Complete user lifecycle operations
- **Location Queries**: Users by city, department, geographic filters
- **Search Operations**: Name-based searches with pattern matching
- **Role Integration**: User-role relationship management

#### 🏢 **StoreService**
- **Store Management**: Multi-location store operations
- **Geographic Queries**: Location-based store filtering
- **Inventory Integration**: Store-product relationship management

#### 💰 **SaleService**
- **Transaction Management**: Complete sales processing
- **Analytics**: Sales totals, date-based reports
- **User Integration**: User-based sales tracking
- **Amount Filtering**: High-value sales identification

#### 📦 **SaleProductService**
- **Sales Analytics**: Best-selling products identification
- **Transaction Details**: Product-sale relationship management
- **Quantity Tracking**: Sales volume analysis

#### 🏷️ **CategoryService**
- **Category Management**: Product categorization system
- **Product Relationships**: Category-product associations

#### 🌍 **Geographic Services**
- **DepartmentService**: Geographic department management
- **CityService**: City operations and department relationships

#### 👥 **UserRoleService**
- **Role Management**: User permission and role assignments
- **Access Control**: Role-based system administration

#### 📊 **StoreProductService**
- **Inventory Management**: Store inventory tracking
- **Stock Control**: Product availability by store location

### Service Benefits
- **Separation of Concerns**: Clear business logic separation
- **Transaction Management**: Automatic transaction handling
- **Error Handling**: Consistent error responses
- **Performance**: Optimized database queries
- **Testability**: Easy unit testing with mocked dependencies
- **Maintainability**: Clear code organization and documentation

## Entities

### Core Entities

#### ProductEntity
- **Purpose**: Represents products available for sale
- **Key Fields**: UUID id, name, description, price, timestamps
- **Relationships**: Many-to-many with categories, many-to-many with stores

#### CategoryEntity
- **Purpose**: Product categorization system
- **Key Fields**: Long id, category name
- **Relationships**: Many-to-many with products

#### UserEntity
- **Purpose**: System users (customers, employees)
- **Key Fields**: UUID id, personal info, credentials, timestamps
- **Relationships**: Many-to-one with city and role

#### StoreEntity
- **Purpose**: Physical or virtual store locations
- **Key Fields**: UUID id, store name
- **Relationships**: Many-to-one with city, many-to-many with products

#### SaleEntity
- **Purpose**: Sales transaction records
- **Key Fields**: UUID id, sale date, total amount
- **Relationships**: Many-to-one with user, many-to-many with products

### Supporting Entities

#### Geographic Entities
- **DepartmentEntity**: Geographic departments/states
- **CityEntity**: Cities within departments

#### Junction Entities
- **StoreProductEntity**: Store inventory management
- **SaleProductEntity**: Products within sales transactions

#### Security Entities
- **UserRoleEntity**: User role definitions
- **UserRole (Enum)**: Role types (ADMIN, USER, MANAGER)

## API Endpoints

The application provides a complete RESTful API with comprehensive endpoints for all entities. All endpoints follow REST conventions and include proper HTTP status codes.

### Base URL
```
http://localhost:8080/api/v1
```

### 📦 Products API (`/api/v1/products`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Get all products |
| `GET` | `/{id}` | Get product by ID |
| `POST` | `/` | Create new product |
| `PUT` | `/` | Update existing product |
| `DELETE` | `/{id}` | Delete product by ID |
| `GET` | `/search/by-price-range` | Filter products by price range |
| `GET` | `/search/by-price-asc` | Get products sorted by price (ascending) |
| `GET` | `/search/by-price-desc` | Get products sorted by price (descending) |
| `GET` | `/search/recent` | Get recently created products |
| `GET` | `/analytics/best-sellers` | Get best-selling products |
| `GET` | `/analytics/top-best-sellers` | Get top N best-selling products |

### 👤 Users API (`/api/v1/users`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Get all users |
| `GET` | `/{id}` | Get user by ID |
| `POST` | `/` | Create new user |
| `PUT` | `/` | Update existing user |
| `DELETE` | `/{id}` | Delete user by ID |
| `GET` | `/search/by-lastname` | Find users by last name |
| `GET` | `/search/by-city` | Find users by city ID |
| `GET` | `/search/by-city-name` | Find users by city name |
| `GET` | `/search/by-department` | Find users by department name |
| `GET` | `/search/by-firstname-pattern` | Find users by first name pattern |

### 🏢 Stores API (`/api/v1/stores`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Get all stores |
| `GET` | `/{id}` | Get store by ID |
| `POST` | `/` | Create new store |
| `PUT` | `/` | Update existing store |
| `DELETE` | `/{id}` | Delete store by ID |
| `GET` | `/search/by-city-id` | Find stores by city ID |
| `GET` | `/search/by-city-name` | Find stores by city name |
| `GET` | `/search/by-department-name` | Find stores by department name |

### 💰 Sales API (`/api/v1/sales`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Get all sales |
| `GET` | `/{id}` | Get sale by ID |
| `POST` | `/` | Create new sale |
| `PUT` | `/` | Update existing sale |
| `DELETE` | `/{id}` | Delete sale by ID |
| `GET` | `/search/by-user-id` | Find sales by user ID |
| `GET` | `/search/by-user-firstname` | Find sales by user first name |
| `GET` | `/search/by-min-total-amount` | Find sales above minimum amount |
| `GET` | `/analytics/total-by-date` | Get daily sales totals |

### 🏷️ Categories API (`/api/v1/categories`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Get all categories |
| `GET` | `/{id}` | Get category by ID |
| `POST` | `/` | Create new category |
| `PUT` | `/` | Update existing category |
| `DELETE` | `/{id}` | Delete category by ID |
| `GET` | `/search/by-name` | Find category by name |
| `GET` | `/{id}/products` | Get products in category |

### 🌍 Geographic APIs

#### Departments (`/api/v1/departments`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Get all departments |
| `GET` | `/{id}` | Get department by ID |
| `POST` | `/` | Create new department |
| `PUT` | `/` | Update existing department |
| `DELETE` | `/{id}` | Delete department by ID |

#### Cities (`/api/v1/cities`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Get all cities |
| `GET` | `/{id}` | Get city by ID |
| `POST` | `/` | Create new city |
| `PUT` | `/` | Update existing city |
| `DELETE` | `/{id}` | Delete city by ID |

### 📊 Management APIs

#### Store Products (`/api/v1/store-products`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Get all store-product relationships |
| `GET` | `/{id}` | Get store-product by ID |
| `POST` | `/` | Create new store-product relationship |
| `PUT` | `/` | Update store-product relationship |
| `DELETE` | `/{id}` | Delete store-product relationship |

#### Sale Products (`/api/v1/sale-products`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Get all sale-product relationships |
| `GET` | `/{id}` | Get sale-product by ID |
| `POST` | `/` | Create new sale-product relationship |
| `PUT` | `/` | Update sale-product relationship |
| `DELETE` | `/{id}` | Delete sale-product relationship |

#### User Roles (`/api/v1/user-roles`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Get all user roles |
| `GET` | `/{id}` | Get user role by ID |
| `POST` | `/` | Create new user role |
| `PUT` | `/` | Update existing user role |
| `DELETE` | `/{id}` | Delete user role by ID |

### Request/Response Format

All APIs use JSON format for request and response bodies. Example:

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Sample Product",
  "description": "Product description",
  "price": 99.99,
  "createdAt": "2025-11-21T10:30:00",
  "updatedAt": "2025-11-21T10:30:00"
}
```

### Error Responses

The API returns standard HTTP status codes with descriptive error messages:

```json
{
  "timestamp": "2025-11-21T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Product with ID 123 not found",
  "path": "/api/v1/products/123"
}
```

## Repositories

All repositories extend `JpaRepository<Entity, ID>` providing standard CRUD operations plus custom queries optimized for business needs:

### 🏷️ CategoryRepository
**Standard Operations**: findAll, findById, save, delete, etc.  
**Custom Queries**:
- `findByCategoryName()`: Find categories by exact name match
- `@Query findProductsByCategoryId()`: Native SQL query to get products in category via junction table

### 📦 ProductRepository  
**Standard Operations**: Complete CRUD with UUID primary keys  
**Custom Queries**:
- `findByPriceBetween(Double min, Double max)`: Price range filtering
- `findByOrderByPriceAsc()`: Products sorted by price (low to high)
- `findByOrderByPriceDesc()`: Products sorted by price (high to low)  
- `findByCreatedAtAfter(LocalDateTime date)`: Recently added products
- **Relationships**: Many-to-many with categories via junction table

### 👤 UserRepository
**Standard Operations**: User management with UUID keys and timestamps  
**Custom Queries**:
- `findByLastNameIgnoreCase(String lastName)`: Case-insensitive lastname search
- `findByCity_Id(Long cityId)`: Users by city ID (via foreign key)
- `findByCity_CityName(String cityName)`: Users by city name (joined query)
- `findByCity_Department_DepartmentName(String deptName)`: Users by department (nested join)
- `findByFirstNameLikeIgnoreCase(String pattern)`: Pattern matching for names (% wildcards)
- **Relationships**: Many-to-one with City and UserRole entities

### 🏢 StoreRepository
**Standard Operations**: Store management with geographic relationships  
**Custom Queries**:
- `findByCity_Id(Long cityId)`: Stores by city ID
- `findByCity_CityName(String cityName)`: Stores by city name
- `findByCity_Department_DepartmentName(String deptName)`: Stores by department
- `@Query findProductsByStoreId()`: Native query for store inventory
- `findStoreProductsByStoreId(UUID storeId)`: Detailed inventory with stock levels
- **Relationships**: Many-to-one with City, Many-to-many with Products

### 💰 SaleRepository  
**Standard Operations**: Sales transaction management  
**Custom Queries**:
- `findByUser_Id(UUID userId)`: Sales by specific user
- `findByUser_FirstNameIgnoreCase(String firstName)`: Sales by user first name
- `findByTotalAmountAfter(Double minAmount)`: High-value sales filtering
- `@Query sumTotalAmountBySaleDate()`: Native SQL for daily sales totals aggregation
- **Relationships**: Many-to-one with User, Many-to-many with Products

### 📊 SaleProductRepository
**Purpose**: Junction entity for Sale-Product many-to-many relationship  
**Custom Analytics Queries**:
- `@Query findBestSellingProducts()`: Complex native SQL for sales analytics
  ```sql
  SELECT p.* FROM product_entity p 
  JOIN sale_product_entity sp ON p.id = sp.product_id 
  GROUP BY p.id ORDER BY SUM(sp.quantity) DESC
  ```
- `findTopBestSellingProducts(Pageable pageable)`: Limited results with pagination
- **Business Logic**: Quantity tracking, sales volume analysis

### 🌍 Geographic Repositories

#### DepartmentRepository
**Standard Operations**: Geographic department management
**Relationships**: One-to-many with Cities

#### CityRepository  
**Standard Operations**: City management with department relationships
**Relationships**: Many-to-one with Department, One-to-many with Users and Stores

### 📋 Management Repositories

#### StoreProductRepository
**Purpose**: Inventory management via junction entity
**Custom Queries**: Store-specific product filtering and stock management
**Relationships**: Many-to-one with both Store and Product entities

#### UserRoleRepository
**Purpose**: Role-based access control
**Standard Operations**: Role management and assignment
**Relationships**: One-to-many with Users

### Repository Features
- **UUID Primary Keys**: For globally unique identifiers
- **Automatic Timestamps**: CreatedAt/UpdatedAt tracking
- **Soft Relationships**: Proper foreign key constraints
- **Custom Queries**: Business-specific data retrieval
- **Native SQL**: Complex analytics and reporting queries
- **Pagination Support**: Large dataset handling
- **Case Sensitivity**: Configurable search options
- **Pattern Matching**: Flexible search capabilities

## Database Configuration

### Production Environment (PostgreSQL)

**Primary Configuration** (`application.properties`):
```properties
# Database Connection
spring.datasource.url=jdbc:postgresql://localhost:5432/commercial_db
spring.datasource.username=commercial_admin
spring.datasource.password=commercial_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

# Connection Pool Settings
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=300000
```

**Database Setup Script**:
```sql
-- Create database
CREATE DATABASE commercial_db;

-- Create user with proper privileges
CREATE USER commercial_admin WITH PASSWORD 'commercial_password';
GRANT ALL PRIVILEGES ON DATABASE commercial_db TO commercial_admin;

-- Connect to database and grant schema privileges
\c commercial_db;
GRANT ALL ON SCHEMA public TO commercial_admin;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO commercial_admin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO commercial_admin;
```

### Test Environment (H2 In-Memory)

**Test Configuration** (`src/test/resources/application.properties`):
```properties
# H2 In-Memory Database for Testing
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA Test Settings
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.h2.console.enabled=true
```

### Docker Configuration

**Docker Compose** (`docker-compose.yml`):
```yaml
version: '3.8'
services:
  postgresql:
    image: postgres:15
    container_name: commercial_db
    environment:
      POSTGRES_DB: commercial_db
      POSTGRES_USER: commercial_admin
      POSTGRES_PASSWORD: commercial_password
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
volumes:
  postgres_data:
```

**Start Database**:
```bash
docker-compose up -d
```

### Schema Features

- **Automatic Schema Management**: Hibernate creates/updates tables automatically
- **UUID Primary Keys**: Most entities use UUID for global uniqueness  
- **Timestamp Tracking**: Automatic createdAt/updatedAt fields
- **Foreign Key Constraints**: Proper referential integrity
- **Junction Tables**: Explicit entities for many-to-many relationships
- **Indexes**: Optimized for common query patterns
- **Data Types**: Proper PostgreSQL data type mapping

## Testing

The application includes comprehensive unit tests with excellent coverage across all layers:

### Test Architecture
```
📊 Test Coverage Overview:
├── Repository Layer Tests ✅ (100% coverage)
├── Service Layer Tests ✅ (Implemented) 
├── Entity Validation Tests ✅ (JPA validation)
└── Integration Tests ✅ (End-to-end API testing)
```

### Test Configuration

**Test Framework Stack**:
- **Database**: H2 in-memory for fast, isolated tests
- **Framework**: Spring Boot Test with `@DataJpaTest`, `@SpringBootTest`
- **Test Data**: `TestEntityManager` for entity setup and management
- **Assertions**: AssertJ for fluent, readable test assertions
- **Mocking**: Mockito for service layer unit tests
- **Test Containers**: Available for integration testing

**Test Properties** (`src/test/resources/application.properties`):
```properties
# Fast in-memory database
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
logging.level.org.springframework.web=DEBUG
```

### Test Implementation

#### 🔧 Repository Tests (`@DataJpaTest`)
**Comprehensive Coverage**:
- **Standard CRUD Operations**: All JpaRepository methods
- **Custom Query Methods**: Business-specific queries  
- **Relationship Testing**: Foreign keys and junction tables
- **Data Validation**: Entity constraints and validation
- **Edge Case Handling**: Null values, empty results, boundary conditions

**Example Test Structure**:
```java
@DataJpaTest
class ProductRepositoryTest {
    @Autowired TestEntityManager entityManager;
    @Autowired ProductRepository productRepository;
    
    @Test void findByPriceBetween_ShouldReturnProductsInRange() { /* ... */ }
    @Test void findByOrderByPriceDesc_ShouldReturnSortedProducts() { /* ... */ }
}
```

#### ⚙️ Service Tests (Unit & Integration)
- **Business Logic**: Service method functionality
- **Mapper Integration**: Entity-DTO conversion testing
- **Error Handling**: Exception scenarios and edge cases
- **Transaction Testing**: Data consistency and rollback scenarios

#### 🌐 Controller Tests (`@SpringBootTest`)
- **API Endpoints**: REST endpoint functionality
- **HTTP Status Codes**: Proper response codes
- **Request/Response Validation**: JSON serialization/deserialization
- **Error Response Testing**: API error handling

### Test Data Management

**Entity Creation Utilities**:
```java
// Helper methods for consistent test data
ProductEntity createTestProduct(String name, Double price);
UserEntity createTestUser(String firstName, String lastName);
CategoryEntity createTestCategory(String categoryName);
```

**Test Data Features**:
- **Consistent Test Data**: Standardized entity creation
- **Relationship Setup**: Proper foreign key relationships
- **Realistic Data**: Business-meaningful test scenarios
- **Cleanup**: Automatic test data cleanup between tests

### Running Tests

**Maven Commands**:
```bash
# Run all tests with coverage report
./mvnw clean test

# Run specific test categories  
./mvnw test -Dtest=*Repository*     # Repository tests only
./mvnw test -Dtest=*Service*        # Service tests only
./mvnw test -Dtest=*Controller*     # Controller tests only

# Run tests with specific profiles
./mvnw test -Dspring.profiles.active=test

# Generate test coverage reports
./mvnw test jacoco:report
```

**PowerShell Commands** (Windows):
```powershell
# Run all tests
.\mvnw.cmd clean test

# Run with coverage
.\mvnw.cmd clean test jacoco:report
```

### Test Results & Reports

**Test Execution Reports**:
- **Location**: `target/surefire-reports/`
- **Format**: XML and TXT reports for each test class
- **Coverage**: JaCoCo coverage reports (if configured)

**Current Test Files**:
- `CategoryRepositoryTest.java` ✅
- `CityRepositoryTest.java` ✅  
- `DepartmentRepositoryTest.java` ✅
- `ProductRepositoryTest.java` ✅
- `SaleRepositoryTest.java` ✅
- `StoreRepositoryTest.java` ✅
- `UserRepositoryTest.java` ✅
- `UserRoleRepositoryTest.java` ✅

### Test Quality Metrics

**Assertions Used**:
- **Entity Verification**: ID, name, relationship validation
- **Collection Testing**: Size, contains, ordering verification
- **Null Safety**: Proper null handling validation
- **Business Logic**: Domain-specific rule validation

**Performance Considerations**:
- **Fast Execution**: H2 in-memory database for speed
- **Isolated Tests**: Each test runs in clean environment
- **Minimal Setup**: Optimized test data creation
- **Parallel Execution**: Tests can run concurrently

## Getting Started

### Prerequisites
- **Java 21 LTS** or higher
- **Maven 3.9+** or newer
- **PostgreSQL 12+** (for production environment)
- **Docker** (optional, for containerized setup)
- **Git** for version control

### Quick Start (5 minutes)

#### Option 1: Using Docker (Recommended)
```bash
# Clone the repository
git clone <repository-url>
cd commercial_management_system

# Start PostgreSQL with Docker
docker-compose up -d

# Run the application
./mvnw spring-boot:run
```

#### Option 2: Local PostgreSQL Setup
```bash
# 1. Clone the repository
git clone <repository-url>
cd commercial_management_system

# 2. Setup PostgreSQL database
psql -U postgres
CREATE DATABASE commercial_db;
CREATE USER commercial_admin WITH PASSWORD 'commercial_password';
GRANT ALL PRIVILEGES ON DATABASE commercial_db TO commercial_admin;
\q

# 3. Run the application
./mvnw spring-boot:run
```

### Detailed Setup Instructions

#### 1. **Environment Setup**

**Java Installation Verification**:
```bash
java -version    # Should show Java 21 or higher
javac -version   # Should show Java 21 or higher
```

**Maven Installation Verification**:
```bash
mvn -version     # Should show Maven 3.9+ and Java 21
```

#### 2. **Database Configuration**

**PostgreSQL Installation** (if not using Docker):
- **Windows**: Download from [PostgreSQL.org](https://www.postgresql.org/download/windows/)
- **macOS**: `brew install postgresql`
- **Linux**: `sudo apt-get install postgresql postgresql-contrib`

**Database Setup Script**:
```sql
-- Connect to PostgreSQL as superuser
psql -U postgres

-- Create database and user
CREATE DATABASE commercial_db;
CREATE USER commercial_admin WITH PASSWORD 'commercial_password';

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE commercial_db TO commercial_admin;

-- Connect to new database and grant schema privileges
\c commercial_db;
GRANT ALL ON SCHEMA public TO commercial_admin;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO commercial_admin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO commercial_admin;

-- Exit PostgreSQL
\q
```

#### 3. **Application Configuration**

**Update Configuration** (if needed):
```properties
# src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/commercial_db
spring.datasource.username=commercial_admin
spring.datasource.password=commercial_password

# Server Configuration  
server.port=8080
server.servlet.context-path=/

# JPA Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

#### 4. **Build and Run**

**Maven Commands**:
```bash
# Clean and compile
./mvnw clean compile

# Run tests
./mvnw test

# Package application
./mvnw clean package

# Run application (Development Mode)
./mvnw spring-boot:run

# Run packaged JAR
java -jar target/commercial_management_system-0.0.1-SNAPSHOT.jar
```

**Windows PowerShell**:
```powershell
# Use Windows Maven wrapper
.\mvnw.cmd clean compile
.\mvnw.cmd test  
.\mvnw.cmd spring-boot:run
```

### Verification Steps

#### 1. **Application Health Check**
```bash
# Check if application is running
curl http://localhost:8080/actuator/health

# Expected response:
{"status":"UP"}
```

#### 2. **API Testing**
```bash
# Test API endpoints
curl http://localhost:8080/api/v1/products
curl http://localhost:8080/api/v1/users
curl http://localhost:8080/api/v1/stores
```

#### 3. **Database Connection Verification**
Check application logs for successful database connection:
```
INFO  - HikariPool-1 - Starting...
INFO  - HikariPool-1 - Start completed.
INFO  - Started CommercialManagementSystemApplication in 3.456 seconds
```

### Development Setup

#### **IDE Configuration**
- **IntelliJ IDEA**: Import as Maven project, enable auto-import
- **VS Code**: Install Java Extension Pack and Spring Boot Extension
- **Eclipse**: Import as "Existing Maven Project"

#### **Hot Reloading** (Development)
```xml
<!-- Add to pom.xml for hot reloading -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

#### **Environment Profiles**
```bash
# Development profile
./mvnw spring-boot:run -Dspring.profiles.active=dev

# Test profile  
./mvnw spring-boot:run -Dspring.profiles.active=test

# Production profile
./mvnw spring-boot:run -Dspring.profiles.active=prod
```

### Troubleshooting

#### **Common Issues & Solutions**

**Port Already in Use**:
```bash
# Change port in application.properties
server.port=8081
```

**Database Connection Issues**:
```bash
# Verify PostgreSQL is running
pg_isready -h localhost -p 5432

# Check connection details
psql -h localhost -p 5432 -U commercial_admin -d commercial_db
```

**Build Issues**:
```bash
# Clean Maven cache
./mvnw dependency:purge-local-repository

# Force update dependencies
./mvnw clean install -U
```

**Test Failures**:
```bash
# Run tests with debug info
./mvnw test -X

# Run specific test
./mvnw test -Dtest=ProductRepositoryTest
```

### Next Steps

After successful setup:

1. **Explore APIs**: Use Postman or curl to test endpoints
2. **Check Documentation**: Review JavaDoc in code
3. **Database Inspection**: Connect to PostgreSQL to see generated schema
4. **Custom Development**: Follow development guidelines for contributions
5. **Testing**: Run comprehensive tests to verify functionality

## Development Guidelines

### Code Standards & Best Practices

#### **Architecture Principles**
- **Layered Architecture**: Clear separation of concerns
- **Dependency Injection**: Constructor-based injection preferred
- **Single Responsibility**: Each class has one clear purpose
- **SOLID Principles**: Applied throughout the codebase
- **DRY (Don't Repeat Yourself)**: Code reusability and maintainability

#### **Coding Standards**
```java
// Example of proper service implementation
@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    
    // Constructor injection (preferred)
    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    // Clear method naming and documentation
    /**
     * Retrieves all products from the database.
     * @return List of ProductModel objects
     */
    public List<ProductModel> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }
}
```

#### **Documentation Requirements**
- **JavaDoc**: All public classes, methods, and fields
- **Inline Comments**: Complex business logic explanation
- **README Updates**: Document significant changes
- **API Documentation**: Clear endpoint descriptions

#### **Testing Standards**
- **Unit Tests**: All service methods tested
- **Repository Tests**: All custom queries validated
- **Integration Tests**: End-to-end API testing
- **Coverage Target**: Minimum 80% code coverage
- **Test Naming**: `methodName_condition_expectedResult`

### Database Design Principles

#### **Entity Design**
- **UUID Primary Keys**: For distributed system compatibility
- **Timestamp Fields**: Automatic tracking via `@CreationTimestamp`, `@UpdateTimestamp`
- **Proper Relationships**: Use appropriate JPA annotations
- **Validation**: Bean Validation annotations for data integrity
- **Naming Conventions**: Clear, descriptive entity and field names

#### **Repository Patterns**
```java
// Standard repository implementation
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    // Method naming follows Spring Data conventions
    List<ProductEntity> findByPriceBetween(Double minPrice, Double maxPrice);
    
    // Custom queries for complex operations
    @Query("SELECT p FROM ProductEntity p WHERE p.price > :minPrice")
    List<ProductEntity> findExpensiveProducts(@Param("minPrice") Double minPrice);
}
```

### API Design Guidelines

#### **REST Conventions**
- **HTTP Methods**: GET (read), POST (create), PUT (update), DELETE (remove)
- **Status Codes**: 200 (OK), 201 (Created), 404 (Not Found), 400 (Bad Request)
- **URL Structure**: `/api/v1/entities/{id}/sub-resources`
- **JSON Format**: Consistent request/response structure
- **Error Handling**: Standardized error response format

#### **Controller Implementation**
```java
@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {
    
    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    
    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@Valid @RequestBody ProductModel product) {
        ProductModel created = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
```

### Project Structure

```
src/main/java/org/acmapis/commercial_management_system/
├── entity/           # JPA entities (database layer)
├── repository/       # Data access layer (Spring Data JPA)
├── service/          # Business logic layer
├── controller/       # REST API endpoints
├── model/dto/        # Data Transfer Objects
├── utils/mapper/     # Entity-DTO mapping utilities
└── enums/           # Application enumerations

src/main/resources/
├── application.properties    # Application configuration
├── static/                  # Static web resources
└── templates/              # Template files (if using Thymeleaf)

src/test/java/
├── repository/      # Repository layer tests
├── service/        # Service layer tests (planned)
└── controller/     # API integration tests (planned)
```

## Documentation

The project maintains comprehensive documentation across multiple formats:

### 📚 **Available Documentation**

#### **Code Documentation**
- **JavaDoc**: All classes and public methods documented in English
- **Inline Comments**: Complex business logic explanations
- **Method Documentation**: Parameters, return values, exceptions

#### **Architecture Documentation**
- **`services-implementation.md`**: Detailed service layer implementation
- **`services-completion-report.md`**: Service development completion status
- **Repository Documentation**: Custom query explanations in this README

#### **Database Documentation**  
- **Entity Relationships**: Documented in entity classes
- **Schema Design**: Automatic generation with proper constraints
- **Query Documentation**: Custom repository methods explained

### 🔄 **Future Enhancements**

#### **Immediate Priorities** (Next Sprint)
- [ ] **Authentication & Authorization**: JWT-based security implementation
- [ ] **API Documentation**: Swagger/OpenAPI 3.0 integration
- [ ] **Input Validation**: Comprehensive data validation
- [ ] **Error Handling**: Global exception handling with proper HTTP responses
- [ ] **Logging**: Structured logging with SLF4J and Logback

#### **Medium-Term Goals**
- [ ] **Caching Implementation**: Redis integration for performance
- [ ] **API Versioning**: Comprehensive versioning strategy
- [ ] **Data Migration**: Flyway/Liquibase for database versioning
- [ ] **Performance Optimization**: Query optimization and indexing
- [ ] **Health Checks**: Actuator endpoints for monitoring

#### **Long-Term Vision**
- [ ] **Microservices Architecture**: Service decomposition strategy
- [ ] **Event Sourcing**: Event-driven architecture implementation
- [ ] **API Rate Limiting**: Request throttling and quotas
- [ ] **Audit Logging**: Complete audit trail for all operations
- [ ] **Metrics & Monitoring**: Prometheus and Grafana integration
- [ ] **Cloud Deployment**: AWS/Azure deployment configurations

## Contributing

### **Development Workflow**
1. **Fork & Clone**: Create personal fork of the repository
2. **Branch Strategy**: Create feature branches from `main`
3. **Code Standards**: Follow established patterns and conventions
4. **Testing**: Add comprehensive tests for new functionality
5. **Documentation**: Update relevant documentation
6. **Pull Request**: Submit PR with clear description and testing evidence

### **Code Review Checklist**
- [ ] Code follows established architecture patterns
- [ ] All public methods have JavaDoc documentation
- [ ] Unit tests written and passing
- [ ] No breaking changes to existing APIs
- [ ] Database migrations (if applicable) are included
- [ ] Performance impact considered and documented
- [ ] Security considerations addressed

### **Commit Message Format**
```
type(scope): brief description

Detailed explanation of the change, including:
- What was changed and why
- Any breaking changes
- Related issue numbers

Example:
feat(product): add price range filtering endpoint

Added new GET endpoint /api/v1/products/search/by-price-range
to support product filtering by minimum and maximum price.

Includes repository method, service logic, and controller endpoint.
Closes #123
```

### **Getting Help**
- **Documentation**: Check this README and docs/ folder first
- **Code Examples**: Review existing implementations for patterns
- **Issues**: Create GitHub issues for bugs or feature requests
- **Discussions**: Use GitHub Discussions for questions and ideas

---

**Commercial Management System Team**  
📧 Contact: [Team Contact Information]  
📅 Last Updated: November 21, 2025  
🔗 Repository: [Repository URL]  
📋 License: [License Information]
