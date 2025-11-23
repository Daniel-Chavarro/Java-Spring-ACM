package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Entity representing a physical or virtual store in the commercial management system.
 * Stores are locations where products are sold and are associated with specific cities.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Entity
@Table(name = "store")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity {
    /**
     * Unique identifier for the store.
     * Auto-generated using UUID strategy for global uniqueness.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "store_id")
    private UUID storeId;

    /**
     * Name of the store.
     * Cannot be null and has a maximum length of 64 characters.
     */
    @Column(name = "store_name", nullable = false, length = 64)
    private String storeName;

    /**
     * City where the store is located.
     * Represents a many-to-one relationship with the city entity.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id_fk", nullable = false)
    private CityEntity city;
}
