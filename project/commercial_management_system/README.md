# Commercial Management System

A comprehensive Spring Boot application for managing commercial operations including products, sales, stores, users, and inventory management.

## Author
**Commercial Management System Team**
- **Version:** 1.0
- **Since:** 2025-11-19

## Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Entities](#entities)
- [Repositories](#repositories)
- [Database Configuration](#database-configuration)
- [Testing](#testing)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)

## Overview

This application provides a robust backend system for managing:
- **Product Catalog**: Products with categories, pricing, and descriptions
- **Store Management**: Multiple store locations with inventory tracking
- **User Management**: User accounts with role-based permissions
- **Sales Transactions**: Complete sales processing with product relationships
- **Geographic Organization**: Departments and cities for location-based operations

## Architecture

The application follows a layered architecture pattern:
- **Entity Layer**: JPA entities representing the data model
- **Repository Layer**: Data access layer using Spring Data JPA
- **Service Layer**: Business logic (to be implemented)
- **Controller Layer**: REST API endpoints (to be implemented)

### Technology Stack
- **Framework:** Spring Boot 3.5.7
- **Database:** PostgreSQL (Production), H2 (Testing)
- **ORM:** Hibernate/JPA
- **Build Tool:** Maven
- **Java Version:** 21
- **Testing:** JUnit 5, Spring Boot Test

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

## Repositories

All repositories extend `JpaRepository` providing standard CRUD operations plus custom queries:

### CategoryRepository
- `findByCategoryName()`: Find categories by name
- `findProductsByCategoryId()`: Get products in a category (native query)

### ProductRepository
- `findByPriceBetween()`: Price range filtering
- `findByOrderByPriceAsc/Desc()`: Price-based sorting
- `findByCreatedAtAfter()`: Recent products

### UserRepository
- `findByLastNameIgnoreCase()`: Case-insensitive lastname search
- `findByCity_*()`: Location-based user queries
- `findByFirstNameLikeIgnoreCase()`: Pattern matching for names

### StoreRepository
- `findByCity_*()`: Location-based store queries
- `findProductsByStoreId()`: Store inventory queries
- `findStoreProductsByStoreId()`: Detailed inventory with stock levels

### SaleRepository
- `findByUser_*()`: User-based sale queries
- `sumTotalAmountBySaleDate()`: Daily sales totals
- `findByTotalAmountAfter()`: High-value sales filtering

### SaleProductRepository
- `findBestSellingProducts()`: Sales analytics
- `findTopBestSellingProducts()`: Limited best-sellers query

## Database Configuration

### Production Database (PostgreSQL)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/commercial_db
spring.datasource.username=commercial_admin
spring.datasource.password=commercial_password
```

### Test Database (H2 In-Memory)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

## Testing

The application includes comprehensive unit tests for all repository layers:

### Test Configuration
- **Database**: H2 in-memory for fast, isolated tests
- **Framework**: Spring Boot Test with `@DataJpaTest`
- **Test Data**: `TestEntityManager` for entity setup
- **Assertions**: AssertJ for readable test assertions

### Test Coverage
- **Repository Tests**: All repository methods tested
- **Entity Relationships**: Junction table operations
- **Query Validation**: Custom queries and native SQL
- **Edge Cases**: Empty results, null handling, boundary conditions

### Running Tests
```bash
./mvnw test                    # Run all tests
./mvnw test -Dtest=*Repository*  # Run only repository tests
```

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.9+
- PostgreSQL 12+ (for production)

### Setup
1. **Clone the repository**
2. **Configure database** (update `application.properties`)
3. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

### Database Setup
```sql
-- Create database
CREATE DATABASE commercial_db;

-- Create user
CREATE USER commercial_admin WITH PASSWORD 'commercial_password';
GRANT ALL PRIVILEGES ON DATABASE commercial_db TO commercial_admin;
```

## API Documentation

*Note: REST controllers and API endpoints are not yet implemented. This section will be updated when the service and controller layers are added.*

### Planned Endpoints
- `GET /api/products` - List products
- `GET /api/categories/{id}/products` - Products by category  
- `GET /api/stores/{id}/products` - Store inventory
- `POST /api/sales` - Create sale transaction
- `GET /api/analytics/best-sellers` - Sales analytics

## Development Guidelines

### Code Style
- **JavaDoc**: All classes and public methods documented
- **Naming**: Clear, descriptive names following Java conventions
- **Annotations**: Proper JPA annotations and validation
- **Testing**: Comprehensive test coverage for all repository methods

### Database Design
- **UUID**: Used for main entity IDs for global uniqueness
- **Timestamps**: Automatic creation and update tracking
- **Foreign Keys**: Proper referential integrity
- **Junction Tables**: Explicit entities for many-to-many relationships

## Future Enhancements

- [ ] Service layer implementation
- [ ] REST API controllers
- [ ] Authentication and authorization
- [ ] Data validation and error handling
- [ ] API documentation (Swagger/OpenAPI)
- [ ] Caching implementation
- [ ] Performance optimization
- [ ] Audit logging
- [ ] Metrics and monitoring

## Contributing

1. Follow the established code style and documentation patterns
2. Add comprehensive tests for new functionality
3. Update this README for significant changes
4. Ensure all tests pass before submitting changes

---

For questions or support, please contact the Commercial Management System development team.
