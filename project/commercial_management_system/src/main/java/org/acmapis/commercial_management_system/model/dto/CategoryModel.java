package org.acmapis.commercial_management_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Model class representing a product category for data transfer operations.
 * This model is used for API communication and contains the same attributes as CategoryEntity.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel implements Serializable {
    /**
     * Unique identifier for the category.
     */
    private Long categoryId;

    /**
     * Name of the category.
     */
    private String categoryName;

    /**
     * List of products associated with this category.
     */
    private List<ProductModel> products;
}