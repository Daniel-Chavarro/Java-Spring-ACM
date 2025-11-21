package org.acmapis.commercial_management_system.utils.mapper;

import org.acmapis.commercial_management_system.entity.DepartmentEntity;
import org.acmapis.commercial_management_system.model.dto.DepartmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    /**
     * Converts a DepartmentEntity to a DepartmentModel.
     *
     * @param departmentEntity the DepartmentEntity to convert
     * @return the corresponding DepartmentModel
     */
    DepartmentModel toModel(DepartmentEntity departmentEntity);

    /**
     * Converts a DepartmentModel to a DepartmentEntity.
     *
     * @param departmentModel the DepartmentModel to convert
     * @return the corresponding DepartmentEntity
     */
    DepartmentEntity toEntity(DepartmentModel departmentModel);

    /**
     * Converts a list of DepartmentEntity to a list of DepartmentModel.
     *
     * @param departmentEntities the list of DepartmentEntity to convert
     * @return the corresponding list of DepartmentModel
     */
    List<DepartmentModel> toModelList(List<DepartmentEntity> departmentEntities);

    /**
     * Converts a list of DepartmentModel to a list of DepartmentEntity.
     *
     * @param departmentModels the list of DepartmentModel to convert
     * @return the corresponding list of DepartmentEntity
     */
    List<DepartmentEntity> toEntityList(List<DepartmentModel> departmentModels);

    /**
     * Updates an existing DepartmentEntity with data from DepartmentModel, preserving the ID.
     *
     * @param departmentModel the DepartmentModel containing the updated data
     * @param departmentEntity the existing DepartmentEntity to update
     */
    void updateEntityFromModel(DepartmentModel departmentModel, @MappingTarget DepartmentEntity departmentEntity);
}