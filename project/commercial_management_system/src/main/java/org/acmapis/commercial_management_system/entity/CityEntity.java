package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a city in the commercial management system.
 * Cities belong to departments and are used for geographic organization of users and stores.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Entity
@Table(name = "city")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityEntity {
    /**
     * Unique identifier for the city.
     * Auto-generated using database identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;

    /**
     * Name of the city.
     * Cannot be null and has a maximum length of 64 characters.
     */
    @Column(name = "city_name", nullable = false, length = 64)
    private String cityName;

    /**
     * Department to which this city belongs.
     * Represents a many-to-one relationship with the department entity.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id_fk", nullable = false)
    private DepartmentEntity department;
}
