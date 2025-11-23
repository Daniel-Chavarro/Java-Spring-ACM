package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a user in the commercial management system.
 * Users are customers or employees who can make purchases and interact with the system.
 * Each user belongs to a specific city and has an assigned role.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    /**
     * Unique identifier for the user.
     * Auto-generated using UUID strategy for global uniqueness.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    /**
     * First name of the user.
     * Cannot be null and has a maximum length of 32 characters.
     */
    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;

    /**
     * Last name of the user.
     * Cannot be null and has a maximum length of 32 characters.
     */
    @Column(name = "last_name", nullable = false, length = 32)
    private String lastName;

    /**
     * Unique username for system authentication.
     * Cannot be null and must be unique across all users.
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * Email address of the user.
     * Cannot be null and must be unique across all users.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Encrypted password for user authentication.
     * Cannot be null.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Timestamp when the user account was created.
     * Automatically set on entity creation and never updated.
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Phone number of the user.
     * Cannot be null and has a maximum length of 10 characters.
     */
    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    /**
     * Role assigned to this user defining their permissions in the system.
     * Represents a many-to-one relationship with the user role entity.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id_fk", nullable = false)
    private UserRoleEntity role;

    /**
     * City where the user is located.
     * Represents a many-to-one relationship with the city entity.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id_fk", nullable = false)
    private CityEntity city;
}
