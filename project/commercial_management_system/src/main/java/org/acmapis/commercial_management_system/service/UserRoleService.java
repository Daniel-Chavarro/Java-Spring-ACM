package org.acmapis.commercial_management_system.service;

import org.acmapis.commercial_management_system.entity.UserRoleEntity;
import org.acmapis.commercial_management_system.model.dto.UserRoleModel;
import org.acmapis.commercial_management_system.repository.UserRoleRepository;
import org.acmapis.commercial_management_system.utils.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing user role-related business logic.
 * Provides CRUD operations for user role management.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository, UserRoleMapper userRoleMapper) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleMapper = userRoleMapper;
    }

    /**
     * Retrieves all user roles from the database.
     *
     * @return List of UserRoleModel objects representing all user roles
     */
    public List<UserRoleModel> getAllUserRoles() {
        List<UserRoleEntity> entities = userRoleRepository.findAll();
        return userRoleMapper.toModelList(entities);
    }

    /**
     * Retrieves a user role by its unique identifier.
     *
     * @param userRoleId The unique identifier of the user role
     * @return Optional containing the UserRoleModel if found, empty otherwise
     */
    public Optional<UserRoleModel> getUserRoleById(Long userRoleId) {
        Optional<UserRoleEntity> entity = userRoleRepository.findById(userRoleId);
        return entity.map(userRoleMapper::toModel);
    }

    /**
     * Creates a new user role in the database.
     *
     * @param userRoleModel The UserRoleModel containing the user role data to save
     * @return The saved UserRoleModel with generated ID
     */
    public UserRoleModel saveUserRole(UserRoleModel userRoleModel) {
        UserRoleEntity entity = userRoleMapper.toEntity(userRoleModel);
        UserRoleEntity savedEntity = userRoleRepository.save(entity);
        return userRoleMapper.toModel(savedEntity);
    }

    /**
     * Updates an existing user role in the database.
     *
     * @param userRoleModel The UserRoleModel containing the updated user role data
     * @return The updated UserRoleModel
     */
    public UserRoleModel updateUserRole(UserRoleModel userRoleModel) {
        UserRoleEntity entity = userRoleMapper.toEntity(userRoleModel);
        UserRoleEntity updatedEntity = userRoleRepository.save(entity);
        return userRoleMapper.toModel(updatedEntity);
    }

    /**
     * Deletes a user role by its unique identifier.
     *
     * @param userRoleId The unique identifier of the user role to delete
     */
    public void deleteUserRoleById(Long userRoleId) {
        userRoleRepository.deleteById(userRoleId);
    }
}
