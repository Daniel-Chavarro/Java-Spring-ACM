package org.acmapis.commercial_management_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acmapis.commercial_management_system.model.enums.UserRole;

/**
 * Model class representing a user role for data transfer operations.
 * This model is used for API communication and contains the same attributes as UserRoleEntity.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleModel {
    /**
     * Unique identifier for the user role.
     */
    private Long userRoleId;

    /**
     * The specific role type assigned to users.
     */
    private UserRole role;
}