package org.acmapis.commercial_management_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Model class representing a product for data transfer operations.
 * This model is used for API communication and contains the same attributes as ProductEntity.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel implements Serializable {
    /**
     * Unique identifier for the product.
     */
    private UUID productId;

    /**
     * Name of the product.
     */
    private String productName;

    /**
     * Detailed description of the product.
     */
    private String productDescription;

    /**
     * Price of the product in the system's default currency.
     */
    private Double price;

    /**
     * Timestamp when the product was created.
     */
    private LocalDateTime createdAt;

    /**
     * Timestamp when the product was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * List of categories associated with the product.
     */
    private List<CategoryModel> categories;
}