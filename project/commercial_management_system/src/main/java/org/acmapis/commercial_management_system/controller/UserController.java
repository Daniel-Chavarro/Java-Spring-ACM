package org.acmapis.commercial_management_system.controller;

import org.acmapis.commercial_management_system.model.dto.UserModel;
import org.acmapis.commercial_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST Controller for User management operations.
 * Provides endpoints for CRUD operations and specialized user queries.
 *
 * @author Commercial Management System Team
 * @version 1.0
 * @since 2025-11-21
 */
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users in the system.
     *
     * @return List of all users
     */
    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Get a specific user by ID.
     *
     * @param id The user ID
     * @return User if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable UUID id) {
        Optional<UserModel> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new user.
     *
     * @param user User data to create
     * @return Created user with 201 status
     */
    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        UserModel savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /**
     * Update an existing user.
     *
     * @param id   The user ID to update
     * @param user Updated user data
     * @return Updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID id, @RequestBody UserModel user) {
        user.setUserId(id);
        UserModel updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete a user by ID.
     *
     * @param id The user ID to delete
     * @return 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    // === Specialized Query Endpoints ===

    /**
     * Search users by last name (case-insensitive).
     *
     * @param lastName The last name to search for
     * @return List of users with matching last name
     */
    @GetMapping("/search/by-lastname")
    public ResponseEntity<List<UserModel>> getUsersByLastName(@RequestParam String lastName) {
        List<UserModel> users = userService.getUsersByLastName(lastName);
        return ResponseEntity.ok(users);
    }

    /**
     * Get users by city ID.
     *
     * @param cityId The city ID
     * @return List of users in the specified city
     */
    @GetMapping("/search/by-city")
    public ResponseEntity<List<UserModel>> getUsersByCityId(@RequestParam Long cityId) {
        List<UserModel> users = userService.getUsersByCityId(cityId);
        return ResponseEntity.ok(users);
    }

    /**
     * Get users by city name.
     *
     * @param cityName The city name
     * @return List of users in the specified city
     */
    @GetMapping("/search/by-city-name")
    public ResponseEntity<List<UserModel>> getUsersByCityName(@RequestParam String cityName) {
        List<UserModel> users = userService.getUsersByCityName(cityName);
        return ResponseEntity.ok(users);
    }

    /**
     * Get users by department name.
     *
     * @param departmentName The department name
     * @return List of users in cities within the specified department
     */
    @GetMapping("/search/by-department")
    public ResponseEntity<List<UserModel>> getUsersByDepartmentName(@RequestParam String departmentName) {
        List<UserModel> users = userService.getUsersByDepartmentName(departmentName);
        return ResponseEntity.ok(users);
    }

    /**
     * Search users by first name pattern (supports SQL LIKE wildcards).
     *
     * @param pattern The first name pattern (e.g., "jo%", "%an%")
     * @return List of users matching the pattern
     */
    @GetMapping("/search/by-firstname-pattern")
    public ResponseEntity<List<UserModel>> getUsersByFirstNamePattern(@RequestParam String pattern) {
        List<UserModel> users = userService.getUsersByFirstNamePattern(pattern);
        return ResponseEntity.ok(users);
    }
}