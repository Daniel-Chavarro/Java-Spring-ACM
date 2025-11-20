package org.acmapis.commercial_management_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class representing a store-product inventory relationship for data transfer operations.
 * This model is used for API communication and contains the same attributes as StoreProductEntity.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreProductModel {
    /**
     * Unique identifier for the store-product relationship.
     */
    private Long id;

    /**
     * Current stock quantity of the product in this store.
     */
    private Long stock;

    /**
     * Physical address or location within the store where the product is stored.
     */
    private String address;

    /**
     * Store where the product is located.
     */
    private StoreModel store;

    /**
     * Product that is stored in this store.
     */
    private ProductModel product;
}