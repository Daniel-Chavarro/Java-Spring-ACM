package org.acmapis.commercial_management_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * Model class representing a store for data transfer operations.
 * This model is used for API communication and contains the same attributes as StoreEntity.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreModel implements Serializable {
    /**
     * Unique identifier for the store.
     */
    private UUID storeId;

    /**
     * Name of the store.
     */
    private String storeName;

    /**
     * City where the store is located.
     */
    private CityModel city;
}