package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.CategoryEntity;
import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findByCategoryName(String categoryName);

    @Query(value = "SELECT p.* FROM product p " +
            "INNER JOIN product_category cp ON p.product_id = cp.product_id_fk " +
            "WHERE cp.category_id_fk = :categoryId",
            nativeQuery = true)
    List<ProductEntity> findProductsByCategoryId(@Param("categoryId") Long categoryId);

}
