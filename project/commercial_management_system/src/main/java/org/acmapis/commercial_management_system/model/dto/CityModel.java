package org.acmapis.commercial_management_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class representing a city for data transfer operations.
 * This model is used for API communication and contains the same attributes as CityEntity.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityModel {
    /**
     * Unique identifier for the city.
     */
    private Long cityId;

    /**
     * Name of the city.
     */
    private String cityName;

    /**
     * Department to which this city belongs.
     */
    private DepartmentModel department;
}