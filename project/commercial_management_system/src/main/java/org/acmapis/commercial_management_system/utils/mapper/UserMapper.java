package org.acmapis.commercial_management_system.utils.mapper;

import org.acmapis.commercial_management_system.entity.UserEntity;
import org.acmapis.commercial_management_system.model.dto.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper interface for User-related data transformations.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Mapper(componentModel = "spring", uses = {UserRoleMapper.class, CityMapper.class})
public interface UserMapper {
    /**
     * Converts a UserEntity to a UserModel.
     *
     * @param userEntity the UserEntity to convert
     * @return the corresponding UserModel
     */
    UserModel toModel(UserEntity userEntity);

    /**
     * Converts a UserModel to a UserEntity.
     *
     * @param userModel the UserModel to convert
     * @return the corresponding UserEntity
     */
    UserEntity toEntity(UserModel userModel);

    /**
     * Converts a list of UserEntity to a list of UserModel.
     *
     * @param userEntities the list of UserEntity to convert
     * @return the corresponding list of UserModel
     */
    List<UserModel> toModelList(List<UserEntity> userEntities);

    /**
     * Converts a list of UserModel to a list of UserEntity.
     *
     * @param userModels the list of UserModel to convert
     * @return the corresponding list of UserEntity
     */
    List<UserEntity> toEntityList(List<UserModel> userModels);

    /**
     * Updates an existing UserEntity with data from UserModel, preserving the ID.
     *
     * @param userModel the UserModel containing the updated data
     * @param userEntity the existing UserEntity to update
     */
    void updateEntityFromModel(UserModel userModel, @MappingTarget UserEntity userEntity);
}
