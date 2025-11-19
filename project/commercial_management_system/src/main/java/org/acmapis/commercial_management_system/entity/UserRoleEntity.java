package org.acmapis.commercial_management_system.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acmapis.commercial_management_system.model.enums.UserRole;

/**
 * Entity representing user roles in the commercial management system.
 * User roles define the permissions and access levels for different types of users.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRoleEntity {
    /**
     * Unique identifier for the user role.
     * Auto-generated using database identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private Long userRoleId;

    /**
     * The specific role type assigned to users.
     * Stored as string enumeration and cannot be null.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;
}
