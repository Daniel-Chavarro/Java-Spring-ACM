package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.CategoryEntity;
import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

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
    Optional<CategoryEntity> findByCategoryName(String categoryName);
}
