package org.acmapis.commercial_management_system.controller;

import org.acmapis.commercial_management_system.model.dto.UserRoleModel;
import org.acmapis.commercial_management_system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for UserRole management operations.
 * Provides endpoints for CRUD operations on user roles.
 *
 * @author Commercial Management System Team
 * @version 1.0
 * @since 2025-11-21
 */
@RestController
@RequestMapping("/api/v1/user-roles")
@CrossOrigin(origins = "*")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /**
     * Get all user roles in the system.
     *
     * @return List of all user roles
     */
    @GetMapping
    public ResponseEntity<List<UserRoleModel>> getAllUserRoles() {
        List<UserRoleModel> userRoles = userRoleService.getAllUserRoles();
        return ResponseEntity.ok(userRoles);
    }

    /**
     * Get a specific user role by ID.
     *
     * @param id The user role ID
     * @return UserRole if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserRoleModel> getUserRoleById(@PathVariable Long id) {
        Optional<UserRoleModel> userRole = userRoleService.getUserRoleById(id);
        return userRole.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new user role.
     *
     * @param userRole UserRole data to create
     * @return Created user role with 201 status
     */
    @PostMapping
    public ResponseEntity<UserRoleModel> createUserRole(@RequestBody UserRoleModel userRole) {
        UserRoleModel savedUserRole = userRoleService.saveUserRole(userRole);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserRole);
    }

    /**
     * Update an existing user role.
     *
     * @param id       The user role ID to update
     * @param userRole Updated user role data
     * @return Updated user role
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserRoleModel> updateUserRole(@PathVariable Long id, @RequestBody UserRoleModel userRole) {
        userRole.setUserRoleId(id);
        UserRoleModel updatedUserRole = userRoleService.updateUserRole(id, userRole);
        return ResponseEntity.ok(updatedUserRole);
    }

    /**
     * Delete a user role by ID.
     *
     * @param id The user role ID to delete
     * @return 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long id) {
        userRoleService.deleteUserRoleById(id);
        return ResponseEntity.noContent().build();
    }
}