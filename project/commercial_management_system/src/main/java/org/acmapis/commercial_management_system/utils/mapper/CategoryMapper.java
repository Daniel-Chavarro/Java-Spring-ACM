package org.acmapis.commercial_management_system.utils.mapper;

import org.acmapis.commercial_management_system.entity.CategoryEntity;
import org.acmapis.commercial_management_system.model.dto.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper interface for Category-related data transformations.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CategoryMapper {
    /**
     * Converts a CategoryEntity to a CategoryModel.
     *
     * @param categoryEntity the CategoryEntity to convert
     * @return the corresponding CategoryModel
     */
    CategoryModel toModel(CategoryEntity categoryEntity);

    /**
     * Converts a CategoryModel to a CategoryEntity.
     *
     * @param categoryModel the CategoryModel to convert
     * @return the corresponding CategoryEntity
     */
    CategoryEntity toEntity(CategoryModel categoryModel);

    /**
     * Converts a list of CategoryEntity to a list of CategoryModel.
     *
     * @param categoryEntities the list of CategoryEntity to convert
     * @return the corresponding list of CategoryModel
     */
    List<CategoryModel> toModelList(List<CategoryEntity> categoryEntities);

    /**
     * Converts a list of CategoryModel to a list of CategoryEntity.
     *
     * @param categoryModels the list of CategoryModel to convert
     * @return the corresponding list of CategoryEntity
     */
    List<CategoryEntity> toEntityList(List<CategoryModel> categoryModels);

    /**
     * Updates an existing CategoryEntity with data from CategoryModel, preserving the ID.
     *
     * @param categoryModel  the CategoryModel containing the updated data
     * @param categoryEntity the existing CategoryEntity to update
     */
    void updateEntityFromModel(CategoryModel categoryModel, @MappingTarget CategoryEntity categoryEntity);
}
