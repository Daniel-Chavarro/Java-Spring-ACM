package org.acmapis.commercial_management_system.service;

import org.acmapis.commercial_management_system.entity.DepartmentEntity;
import org.acmapis.commercial_management_system.model.dto.DepartmentModel;
import org.acmapis.commercial_management_system.repository.DepartmentRepository;
import org.acmapis.commercial_management_system.utils.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing department-related business logic.
 * Provides CRUD operations for department management.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Service
public class DepartmentService {

    /** Repository for DepartmentEntity operations. */
    private final DepartmentRepository departmentRepository;
    /** Mapper for converting between DepartmentEntity and DepartmentModel. */
    private final DepartmentMapper departmentMapper;

    /**
     * Constructor for DepartmentService with dependency injection.
     *
     * @param departmentRepository Repository for DepartmentEntity operations
     * @param departmentMapper    Mapper for DepartmentEntity and DepartmentModel
     */
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    /**
     * Retrieves all departments from the database.
     *
     * @return List of DepartmentModel objects representing all departments
     */
    public List<DepartmentModel> getAllDepartments() {
        List<DepartmentEntity> entities = departmentRepository.findAll();
        return departmentMapper.toModelList(entities);
    }

    /**
     * Retrieves a department by its unique identifier.
     *
     * @param departmentId The unique identifier of the department
     * @return Optional containing the DepartmentModel if found, empty otherwise
     */
    public Optional<DepartmentModel> getDepartmentById(Long departmentId) {
        Optional<DepartmentEntity> entity = departmentRepository.findById(departmentId);
        return entity.map(departmentMapper::toModel);
    }

    /**
     * Creates a new department in the database.
     *
     * @param departmentModel The DepartmentModel containing the department data to save
     * @return The saved DepartmentModel with generated ID
     */
    public DepartmentModel saveDepartment(DepartmentModel departmentModel) {
        DepartmentEntity entity = departmentMapper.toEntity(departmentModel);
        DepartmentEntity savedEntity = departmentRepository.save(entity);
        return departmentMapper.toModel(savedEntity);
    }

    /**
     * Updates an existing department in the database.
     *
     * @param departmentModel The DepartmentModel containing the updated department data
     * @return The updated DepartmentModel
     */
    public DepartmentModel updateDepartment(DepartmentModel departmentModel) {
        DepartmentEntity entity = departmentMapper.toEntity(departmentModel);
        DepartmentEntity updatedEntity = departmentRepository.save(entity);
        return departmentMapper.toModel(updatedEntity);
    }

    /**
     * Deletes a department by its unique identifier.
     *
     * @param departmentId The unique identifier of the department to delete
     */
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
