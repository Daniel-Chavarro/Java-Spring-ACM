package org.acmapis.commercial_management_system.utils.mapper;

import org.acmapis.commercial_management_system.entity.CityEntity;
import org.acmapis.commercial_management_system.model.dto.CityModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper interface for City-related data transformations.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface CityMapper {
    /**
     * Converts a CityEntity to a CityModel.
     *
     * @param cityEntity the CityEntity to convert
     * @return the corresponding CityModel
     */
    CityModel toModel(CityEntity cityEntity);

    /**
     * Converts a CityModel to a CityEntity.
     *
     * @param cityModel the CityModel to convert
     * @return the corresponding CityEntity
     */
    CityEntity toEntity(CityModel cityModel);

    /**
     * Converts a list of CityEntity to a list of CityModel.
     *
     * @param cityEntities the list of CityEntity to convert
     * @return the corresponding list of CityModel
     */
    List<CityModel> toModelList(List<CityEntity> cityEntities);

    /**
     * Converts a list of CityModel to a list of CityEntity.
     *
     * @param cityModels the list of CityModel to convert
     * @return the corresponding list of CityEntity
     */
    List<CityEntity> toEntityList(List<CityModel> cityModels);

    /**
     * Updates an existing CityEntity with data from CityModel, preserving the ID.
     *
     * @param cityModel  the CityModel containing the updated data
     * @param cityEntity the existing CityEntity to update
     */
    void updateEntityFromModel(CityModel cityModel, @MappingTarget CityEntity cityEntity);
}
