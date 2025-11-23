package org.acmapis.commercial_management_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Model class representing a sale-product relationship for data transfer operations.
 * This model is used for API communication and contains the same attributes as SaleProductEntity.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleProductModel implements Serializable {
    /**
     * Unique identifier for the sale-product relationship.
     */
    private Long saleProductId;

    /**
     * Quantity of the product purchased in this sale.
     */
    private Long quantity;

    /**
     * Sale transaction that includes this product.
     */
    private SaleModel sale;

    /**
     * Product that was sold in this transaction.
     */
    private ProductModel product;
}