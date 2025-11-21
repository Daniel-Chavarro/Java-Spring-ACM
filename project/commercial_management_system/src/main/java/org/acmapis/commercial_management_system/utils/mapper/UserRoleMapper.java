package org.acmapis.commercial_management_system.utils.mapper;

import org.acmapis.commercial_management_system.entity.UserRoleEntity;
import org.acmapis.commercial_management_system.model.dto.UserRoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper interface for UserRole-related data transformations.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    /**
     * Converts a UserRoleEntity to a UserRoleModel.
     *
     * @param userRoleEntity the UserRoleEntity to convert
     * @return the corresponding UserRoleModel
     */
    UserRoleModel toModel(UserRoleEntity userRoleEntity);

    /**
     * Converts a UserRoleModel to a UserRoleEntity.
     *
     * @param userRoleModel the UserRoleModel to convert
     * @return the corresponding UserRoleEntity
     */
    UserRoleEntity toEntity(UserRoleModel userRoleModel);

    /**
     * Converts a list of UserRoleEntity to a list of UserRoleModel.
     *
     * @param userRoleEntities the list of UserRoleEntity to convert
     * @return the corresponding list of UserRoleModel
     */
    List<UserRoleModel> toModelList(List<UserRoleEntity> userRoleEntities);

    /**
     * Converts a list of UserRoleModel to a list of UserRoleEntity.
     *
     * @param userRoleModels the list of UserRoleModel to convert
     * @return the corresponding list of UserRoleEntity
     */
    List<UserRoleEntity> toEntityList(List<UserRoleModel> userRoleModels);

    /**
     * Updates an existing UserRoleEntity with data from UserRoleModel, preserving the ID.
     *
     * @param userRoleModel the UserRoleModel containing the updated data
     * @param userRoleEntity the existing UserRoleEntity to update
     */
    void updateEntityFromModel(UserRoleModel userRoleModel, @MappingTarget UserRoleEntity userRoleEntity);
}


