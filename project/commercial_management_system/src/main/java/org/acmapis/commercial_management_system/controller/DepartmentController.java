package org.acmapis.commercial_management_system.controller;

import org.acmapis.commercial_management_system.model.dto.DepartmentModel;
import org.acmapis.commercial_management_system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Department management operations.
 * Provides endpoints for CRUD operations on departments.
 *
 * @author Commercial Management System Team
 * @version 1.0
 * @since 2025-11-21
 */
@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Get all departments in the system.
     *
     * @return List of all departments
     */
    @GetMapping
    public ResponseEntity<List<DepartmentModel>> getAllDepartments() {
        List<DepartmentModel> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    /**
     * Get a specific department by ID.
     *
     * @param id The department ID
     * @return Department if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentModel> getDepartmentById(@PathVariable Long id) {
        Optional<DepartmentModel> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new department.
     *
     * @param department Department data to create
     * @return Created department with 201 status
     */
    @PostMapping
    public ResponseEntity<DepartmentModel> createDepartment(@RequestBody DepartmentModel department) {
        DepartmentModel savedDepartment = departmentService.saveDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }

    /**
     * Update an existing department.
     *
     * @param id         The department ID to update
     * @param department Updated department data
     * @return Updated department
     */
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentModel> updateDepartment(@PathVariable Long id, @RequestBody DepartmentModel department) {
        department.setDepartmentId(id);
        DepartmentModel updatedDepartment = departmentService.updateDepartment(id, department);
        return ResponseEntity.ok(updatedDepartment);
    }

    /**
     * Delete a department by ID.
     *
     * @param id The department ID to delete
     * @return 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.noContent().build();
    }
}