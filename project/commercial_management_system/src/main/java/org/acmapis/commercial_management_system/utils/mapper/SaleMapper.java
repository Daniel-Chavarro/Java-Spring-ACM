package org.acmapis.commercial_management_system.utils.mapper;

import org.acmapis.commercial_management_system.entity.SaleEntity;
import org.acmapis.commercial_management_system.model.dto.SaleModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper interface for Sale-related data transformations.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface SaleMapper {
    /**
     * Converts a SaleEntity to a SaleModel.
     *
     * @param saleEntity the SaleEntity to convert
     * @return the corresponding SaleModel
     */
    SaleModel toModel(SaleEntity saleEntity);

    /**
     * Converts a SaleModel to a SaleEntity.
     *
     * @param saleModel the SaleModel to convert
     * @return the corresponding SaleEntity
     */
    SaleEntity toEntity(SaleModel saleModel);

    /**
     * Converts a list of SaleEntity to a list of SaleModel.
     *
     * @param saleEntities the list of SaleEntity to convert
     * @return the corresponding list of SaleModel
     */
    List<SaleModel> toModelList(List<SaleEntity> saleEntities);

    /**
     * Converts a list of SaleModel to a list of SaleEntity.
     *
     * @param saleModels the list of SaleModel to convert
     * @return the corresponding list of SaleEntity
     */
    List<SaleEntity> toEntityList(List<SaleModel> saleModels);

    /**
     * Updates an existing SaleEntity with data from SaleModel, preserving the ID.
     *
     * @param saleModel the SaleModel containing the updated data
     * @param saleEntity the existing SaleEntity to update
     */
    void updateEntityFromModel(SaleModel saleModel, @MappingTarget SaleEntity saleEntity);
}
