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

    /**
     * Repository interface for accessing user role data in the database.
     * Provides CRUD operations for user role entities.
     */
    private final UserRoleRepository userRoleRepository;

    /**
     * Mapper interface for converting between UserRoleEntity and UserRoleModel objects.
     * Handles automatic mapping using MapStruct framework.
     */
    private final UserRoleMapper userRoleMapper;

    /**
     * Constructs a new UserRoleService with the required dependencies.
     * Uses constructor-based dependency injection for better testability and immutability.
     *
     * @param userRoleRepository the repository for user role data access operations
     * @param userRoleMapper     the mapper for user role entity-model conversions
     */
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
     * Uses the find-modify-save pattern to ensure data integrity and prevent ID conflicts.
     *
     * @param userRoleId    The unique identifier of the user role to update
     * @param userRoleModel The UserRoleModel containing the updated user role data
     * @return The updated UserRoleModel
     * @throws RuntimeException if the user role with the given ID is not found
     */
    public UserRoleModel updateUserRole(Long userRoleId, UserRoleModel userRoleModel) {
        UserRoleEntity existingEntity = userRoleRepository.findById(userRoleId)
                .orElseThrow(() -> new RuntimeException("UserRole not found with ID: " + userRoleId));

        userRoleMapper.updateEntityFromModel(userRoleModel, existingEntity);

        UserRoleEntity updatedEntity = userRoleRepository.save(existingEntity);
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
