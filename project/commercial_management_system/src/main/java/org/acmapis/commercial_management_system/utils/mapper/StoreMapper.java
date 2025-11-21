package org.acmapis.commercial_management_system.utils.mapper;

import org.acmapis.commercial_management_system.entity.StoreEntity;
import org.acmapis.commercial_management_system.model.dto.StoreModel;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for Store-related data transformations.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface StoreMapper {
    /**
     * Converts a StoreEntity to a StoreModel.
     *
     * @param storeEntity the StoreEntity to convert
     * @return the corresponding StoreModel
     */
    StoreModel toModel(StoreEntity storeEntity);

    /**
     * Converts a StoreModel to a StoreEntity.
     *
     * @param storeModel the StoreModel to convert
     * @return the corresponding StoreEntity
     */
    StoreEntity toEntity(StoreModel storeModel);

    /**
     * Converts a list of StoreEntity to a list of StoreModel.
     *
     * @param storeEntities the list of StoreEntity to convert
     * @return the corresponding list of StoreModel
     */
    List<StoreModel> toModelList(List<StoreEntity> storeEntities);

    /**
     * Converts a list of StoreModel to a list of StoreEntity.
     *
     * @param storeModels the list of StoreModel to convert
     * @return the corresponding list of StoreEntity
     */
    List<StoreEntity> toEntityList(List<StoreModel> storeModels);
}
