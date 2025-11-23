package org.acmapis.commercial_management_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Model class representing a department for data transfer operations.
 * This model is used for API communication and contains the same attributes as DepartmentEntity.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentModel implements Serializable {
    /**
     * Unique identifier for the department.
     */
    private Long departmentId;

    /**
     * Name of the department.
     */
    private String departmentName;
}