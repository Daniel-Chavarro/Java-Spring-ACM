package org.acmapis.commercial_management_system.utils.mapper;

import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.acmapis.commercial_management_system.model.dto.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper interface for Product-related data transformations.
 * Note: The categories list is ignored in toModel to avoid circular references with CategoryMapper.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    /**
     * Converts a ProductEntity to a ProductModel.
     * The categories list is ignored to prevent infinite recursion with CategoryMapper.
     *
     * @param productEntity the ProductEntity to convert
     * @return the corresponding ProductModel (without categories list)
     */
    ProductModel toModel(ProductEntity productEntity);

    /**
     * Converts a ProductModel to a ProductEntity.
     *
     * @param productModel the ProductModel to convert
     * @return the corresponding ProductEntity
     */
    ProductEntity toEntity(ProductModel productModel);

    /**
     * Converts a list of ProductEntity to a list of ProductModel.
     *
     * @param productEntities the list of ProductEntity to convert
     * @return the corresponding list of ProductModel
     */
    List<ProductModel> toModelList(List<ProductEntity> productEntities);

    /**
     * Converts a list of ProductModel to a list of ProductEntity.
     *
     * @param productModels the list of ProductModel to convert
     * @return the corresponding list of ProductEntity
     */
    List<ProductEntity> toEntityList(List<ProductModel> productModels);

    /**
     * Updates an existing ProductEntity with data from ProductModel, preserving the ID.
     *
     * @param productModel  the ProductModel containing the updated data
     * @param productEntity the existing ProductEntity to update
     */
    void updateEntityFromModel(ProductModel productModel, @MappingTarget ProductEntity productEntity);
}