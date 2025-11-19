package org.acmapis.commercial_management_system.model.enums;

import lombok.Getter;

/**
 * Enumeration defining the different user roles available in the commercial management system.
 * Each role represents a different level of access and permissions within the system.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Getter
public enum UserRole {
    /** Administrator role with full system access and permissions */
    ADMIN("ADMIN"),

    /** Regular user role with limited access to basic functionality */
    USER("USER"),

    /** Manager role with moderate permissions for managing operations */
    MANAGER("MODERATOR");

    /**
     * The string representation of the role name.
     */
    private final String roleName;

    /**
     * Constructor for UserRole enum.
     *
     * @param roleName The string representation of the role name
     */
    UserRole(String roleName) {
        this.roleName = roleName;
    }
}
