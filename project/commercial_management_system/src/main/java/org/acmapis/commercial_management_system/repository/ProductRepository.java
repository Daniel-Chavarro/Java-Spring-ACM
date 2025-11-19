package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findByPriceBetween(Double priceAfter, Double priceBefore);

    List<ProductEntity> findByOrderByPriceAsc();

    List<ProductEntity> findByOrderByPriceDesc();

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime createdAtAfter);

}
