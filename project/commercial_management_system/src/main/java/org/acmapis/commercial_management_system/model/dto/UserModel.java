package org.acmapis.commercial_management_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Model class representing a user for data transfer operations.
 * This model is used for API communication and contains the same attributes as UserEntity.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    /**
     * Unique identifier for the user.
     */
    private UUID userId;

    /**
     * First name of the user.
     */
    private String firstName;

    /**
     * Last name of the user.
     */
    private String lastName;

    /**
     * Unique username for system authentication.
     */
    private String username;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * Encrypted password for user authentication.
     * Note: In production, consider excluding password from DTOs for security.
     */
    private String password;

    /**
     * Timestamp when the user account was created.
     */
    private LocalDateTime createdAt;

    /**
     * Phone number of the user.
     */
    private String phone;

    /**
     * Role assigned to this user defining their permissions in the system.
     */
    private UserRoleModel role;

    /**
     * City where the user is located.
     */
    private CityModel city;
}