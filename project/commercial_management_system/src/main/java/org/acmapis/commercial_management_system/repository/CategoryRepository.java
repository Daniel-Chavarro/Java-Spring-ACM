package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.CategoryEntity;
import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing CategoryEntity data access operations.
 * Provides CRUD operations and custom query methods for category-related database interactions.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    /**
     * Finds categories by their name.
     *
     * @param categoryName The name of the category to search for
     * @return List of CategoryEntity objects matching the specified name
     */
    List<CategoryEntity> findByCategoryName(String categoryName);

    /**
     * Retrieves all products that belong to a specific category using native SQL query.
     * This method performs a join operation between product and product_category tables.
     *
     * @param categoryId The unique identifier of the category
     * @return List of ProductEntity objects associated with the specified category
     */
    @Query(value = "SELECT p.* FROM product p " +
            "INNER JOIN product_category cp ON p.product_id = cp.product_id_fk " +
            "WHERE cp.category_id_fk = :categoryId",
            nativeQuery = true)
    List<ProductEntity> findProductsByCategoryId(@Param("categoryId") Long categoryId);

}
