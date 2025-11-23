package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing UserEntity data access operations.
 * Provides CRUD operations and search queries for user management.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    /**
     * Finds users by their last name (case-insensitive).
     *
     * @param lastName The last name to search for (case-insensitive)
     * @return List of UserEntity objects with the specified last name
     */
    List<UserEntity> findByLastNameIgnoreCase(String lastName);

    /**
     * Finds all users located in a specific city by city ID.
     *
     * @param cityCityId The unique identifier of the city
     * @return List of UserEntity objects located in the specified city
     */
    List<UserEntity> findByCity_CityId(Long cityCityId);

    /**
     * Finds all users located in a specific city by city name.
     *
     * @param cityCityName The name of the city
     * @return List of UserEntity objects located in the city with the specified name
     */
    List<UserEntity> findByCity_CityName(String cityCityName);

    /**
     * Finds all users located in cities within a specific department by department name.
     *
     * @param cityDepartmentDepartmentName The name of the department
     * @return List of UserEntity objects located in cities within the specified department
     */
    List<UserEntity> findByCity_Department_DepartmentName(String cityDepartmentDepartmentName);

    /**
     * Finds users by their first name using a case-insensitive partial match.
     *
     * @param firstName The first name pattern to search for (case-insensitive)
     * @return List of UserEntity objects with first names matching the specified pattern
     */
    List<UserEntity> findByFirstNameContainingIgnoreCase(String firstName);
}
