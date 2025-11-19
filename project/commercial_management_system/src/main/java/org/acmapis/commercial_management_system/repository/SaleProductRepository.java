package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.acmapis.commercial_management_system.entity.SaleProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProductEntity, Long> {

    @Query("SELECT sp.product FROM SaleProductEntity sp " +
            "GROUP BY sp.product " +
            "ORDER BY SUM(sp.quantity) DESC")
    List<ProductEntity> findBestSellingProducts();

    @Query("SELECT sp.product FROM SaleProductEntity sp " +
            "GROUP BY sp.product " +
            "ORDER BY SUM(sp.quantity) DESC " +
            "LIMIT :limit")
    List<ProductEntity> findTopBestSellingProducts(@Param("limit") int limit);

}
