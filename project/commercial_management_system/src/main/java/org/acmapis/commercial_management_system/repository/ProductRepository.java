package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing ProductEntity data access operations.
 * Provides CRUD operations and custom query methods for product-related database interactions.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    /**
     * Finds products with price within the specified range.
     *
     * @param priceAfter The minimum price (inclusive)
     * @param priceBefore The maximum price (inclusive)
     * @return List of ProductEntity objects with prices in the specified range
     */
    List<ProductEntity> findByPriceBetween(Double priceAfter, Double priceBefore);

    /**
     * Retrieves all products ordered by price in ascending order.
     *
     * @return List of ProductEntity objects sorted by price from lowest to highest
     */
    List<ProductEntity> findByOrderByPriceAsc();

    /**
     * Retrieves all products ordered by price in descending order.
     *
     * @return List of ProductEntity objects sorted by price from highest to lowest
     */
    List<ProductEntity> findByOrderByPriceDesc();

    /**
     * Finds products created after the specified date and time.
     *
     * @param createdAtAfter The datetime threshold for filtering products
     * @return List of ProductEntity objects created after the specified datetime
     */
    List<ProductEntity> findByCreatedAtAfter(LocalDateTime createdAtAfter);

}
