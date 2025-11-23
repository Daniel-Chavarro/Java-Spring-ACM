package org.acmapis.commercial_management_system.service;

import org.acmapis.commercial_management_system.entity.UserEntity;
import org.acmapis.commercial_management_system.model.dto.UserModel;
import org.acmapis.commercial_management_system.repository.UserRepository;
import org.acmapis.commercial_management_system.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing user-related business logic.
 * Provides CRUD operations and specific queries for user management.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Service
public class UserService {

    /**
     * Repository interface for accessing user data in the database.
     * Provides CRUD operations and custom queries for user entities.
     */
    private final UserRepository userRepository;

    /**
     * Mapper interface for converting between UserEntity and UserModel objects.
     * Handles automatic mapping using MapStruct framework.
     */
    private final UserMapper userMapper;

    /**
     * Constructs a new UserService with the required dependencies.
     * Uses constructor-based dependency injection for better testability and immutability.
     *
     * @param userRepository the repository for user data access operations
     * @param userMapper     the mapper for entity-model conversions
     */
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return List of UserModel objects representing all users
     */
    public List<UserModel> getAllUsers() {
        List<UserEntity> entities = userRepository.findAll();
        return userMapper.toModelList(entities);
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId The unique identifier of the user
     * @return Optional containing the UserModel if found, empty otherwise
     */
    public Optional<UserModel> getUserById(UUID userId) {
        Optional<UserEntity> entity = userRepository.findById(userId);
        return entity.map(userMapper::toModel);
    }

    /**
     * Creates a new user in the database.
     *
     * @param userModel The UserModel containing the user data to save
     * @return The saved UserModel with generated ID
     */
    public UserModel saveUser(UserModel userModel) {
        UserEntity entity = userMapper.toEntity(userModel);
        UserEntity savedEntity = userRepository.save(entity);
        return userMapper.toModel(savedEntity);
    }

    /**
     * Updates an existing user in the database.
     * Uses the find-modify-save pattern to ensure data integrity and prevent ID conflicts.
     *
     * @param userId    The unique identifier of the user to update
     * @param userModel The UserModel containing the updated user data
     * @return The updated UserModel
     * @throws RuntimeException if the user with the given ID is not found
     */
    public UserModel updateUser(UUID userId, UserModel userModel) {
        UserEntity existingEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        userMapper.updateEntityFromModel(userModel, existingEntity);

        UserEntity updatedEntity = userRepository.save(existingEntity);
        return userMapper.toModel(updatedEntity);
    }

    /**
     * Deletes a user by their unique identifier.
     *
     * @param userId The unique identifier of the user to delete
     */
    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }

    // Custom query methods

    /**
     * Finds users by their last name (case-insensitive).
     *
     * @param lastName The last name to search for
     * @return List of UserModel objects with the specified last name
     */
    public List<UserModel> getUsersByLastName(String lastName) {
        List<UserEntity> entities = userRepository.findByLastNameIgnoreCase(lastName);
        return userMapper.toModelList(entities);
    }

    /**
     * Finds all users located in a specific city by city ID.
     *
     * @param cityId The unique identifier of the city
     * @return List of UserModel objects located in the specified city
     */
    public List<UserModel> getUsersByCityId(Long cityId) {
        List<UserEntity> entities = userRepository.findByCity_CityId(cityId);
        return userMapper.toModelList(entities);
    }

    /**
     * Finds all users located in a specific city by city name.
     *
     * @param cityName The name of the city
     * @return List of UserModel objects located in the city with the specified name
     */
    public List<UserModel> getUsersByCityName(String cityName) {
        List<UserEntity> entities = userRepository.findByCity_CityName(cityName);
        return userMapper.toModelList(entities);
    }

    /**
     * Finds all users located in cities within a specific department by department name.
     *
     * @param departmentName The name of the department
     * @return List of UserModel objects located in cities within the specified department
     */
    public List<UserModel> getUsersByDepartmentName(String departmentName) {
        List<UserEntity> entities = userRepository.findByCity_Department_DepartmentName(departmentName);
        return userMapper.toModelList(entities);
    }

    /**
     * Finds users by first name using pattern matching (case-insensitive).
     * Supports SQL LIKE patterns with wildcards (%, _).
     *
     * @param firstName The first name pattern to search for
     * @return List of UserModel objects with first names matching the specified pattern
     */
    public List<UserModel> getUsersByFirstNamePattern(String firstName) {
        List<UserEntity> entities = userRepository.findByFirstNameContainingIgnoreCase(firstName);
        return userMapper.toModelList(entities);
    }
}
