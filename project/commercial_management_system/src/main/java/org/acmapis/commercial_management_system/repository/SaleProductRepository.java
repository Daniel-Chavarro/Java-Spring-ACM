package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.acmapis.commercial_management_system.entity.SaleProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing SaleProductEntity data access operations.
 * Provides CRUD operations and analytics queries for sale-product relationships.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Repository
public interface SaleProductRepository extends JpaRepository<SaleProductEntity, Long> {

    /**
     * Retrieves all products ordered by their total sales quantity in descending order.
     * This method aggregates the quantity sold for each product across all sales.
     *
     * @return List of ProductEntity objects ordered by total sales quantity (highest to lowest)
     */
    @Query("SELECT sp.product FROM SaleProductEntity sp " +
            "GROUP BY sp.product " +
            "ORDER BY SUM(sp.quantity) DESC")
    List<ProductEntity> findBestSellingProducts();

    /**
     * Retrieves the top N best-selling products ordered by total sales quantity.
     *
     * @param limit The maximum number of products to return
     * @return List of ProductEntity objects representing the top best-selling products
     */
    @Query("SELECT sp.product FROM SaleProductEntity sp " +
            "GROUP BY sp.product " +
            "ORDER BY SUM(sp.quantity) DESC " +
            "LIMIT :limit")
    List<ProductEntity> findTopBestSellingProducts(@Param("limit") int limit);

}
