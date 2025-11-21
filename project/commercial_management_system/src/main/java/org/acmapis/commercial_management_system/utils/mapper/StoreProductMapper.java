package org.acmapis.commercial_management_system.utils.mapper;

import org.acmapis.commercial_management_system.entity.StoreProductEntity;
import org.acmapis.commercial_management_system.model.dto.StoreProductModel;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for StoreProduct-related data transformations.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Mapper(componentModel = "spring", uses = {StoreMapper.class, ProductMapper.class})
public interface StoreProductMapper {
    /**
     * Converts a StoreProductEntity to a StoreProductModel.
     *
     * @param storeProductEntity the StoreProductEntity to convert
     * @return the corresponding StoreProductModel
     */
    StoreProductModel toModel(StoreProductEntity storeProductEntity);

    /**
     * Converts a StoreProductModel to a StoreProductEntity.
     *
     * @param storeProductModel the StoreProductModel to convert
     * @return the corresponding StoreProductEntity
     */
    StoreProductEntity toEntity(StoreProductModel storeProductModel);

    /**
     * Converts a list of StoreProductEntity to a list of StoreProductModel.
     *
     * @param storeProductEntities the list of StoreProductEntity to convert
     * @return the corresponding list of StoreProductModel
     */
    List<StoreProductModel> toModelList(List<StoreProductEntity> storeProductEntities);

    /**
     * Converts a list of StoreProductModel to a list of StoreProductEntity.
     *
     * @param storeProductModels the list of StoreProductModel to convert
     * @return the corresponding list of StoreProductEntity
     */
    List<StoreProductEntity> toEntityList(List<StoreProductModel> storeProductModels);
}
