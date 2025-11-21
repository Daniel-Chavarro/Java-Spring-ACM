package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
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
     * @param priceAfter  The minimum price (inclusive)
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

    /**
     * Retrieves all products that belong to a specific category using JPQL.
     * Uses the bidirectional relationship between CategoryEntity and ProductEntity.
     *
     * @param categoryId The unique identifier of the category
     * @return List of ProductEntity objects associated with the specified category
     */
    @Query("SELECT p FROM ProductEntity p JOIN p.categories c WHERE c.categoryId = :categoryId")
    List<ProductEntity> findByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * Retrieves all products that belong to a category with the specified name.
     *
     * @param categoryName The name of the category
     * @return List of ProductEntity objects associated with the specified category name
     */
    @Query("SELECT p FROM ProductEntity p JOIN p.categories c WHERE c.categoryName = :categoryName")
    List<ProductEntity> findByCategoryName(@Param("categoryName") String categoryName);
}
