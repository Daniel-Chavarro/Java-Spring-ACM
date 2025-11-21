package org.acmapis.commercial_management_system.utils.mapper;

import org.acmapis.commercial_management_system.entity.SaleProductEntity;
import org.acmapis.commercial_management_system.model.dto.SaleProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, SaleMapper.class})
public interface SaleProductMapper {
    /**
     * Converts a SaleProductEntity to a SaleProductModel.
     *
     * @param saleProductEntity the SaleProductEntity to convert
     * @return the corresponding SaleProductModel
     */
    SaleProductModel toModel(SaleProductEntity saleProductEntity);

    /**
     * Converts a SaleProductModel to a SaleProductEntity.
     *
     * @param saleProductModel the SaleProductModel to convert
     * @return the corresponding SaleProductEntity
     */
    SaleProductEntity toEntity(SaleProductModel saleProductModel);

    /**
     * Converts a list of SaleProductEntity to a list of SaleProductModel.
     *
     * @param saleProductEntities the list of SaleProductEntity to convert
     * @return the corresponding list of SaleProductModel
     */
    List<SaleProductModel> toModelList(List<SaleProductEntity> saleProductEntities);

    /**
     * Converts a list of SaleProductModel to a list of SaleProductEntity.
     *
     * @param saleProductModels the list of SaleProductModel to convert
     * @return the corresponding list of SaleProductEntity
     */
    List<SaleProductEntity> toEntityList(List<SaleProductModel> saleProductModels);

    /**
     * Updates an existing SaleProductEntity with data from SaleProductModel, preserving the ID.
     *
     * @param saleProductModel  the SaleProductModel containing the updated data
     * @param saleProductEntity the existing SaleProductEntity to update
     */
    void updateEntityFromModel(SaleProductModel saleProductModel, @MappingTarget SaleProductEntity saleProductEntity);
}