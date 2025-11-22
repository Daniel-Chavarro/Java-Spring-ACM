package org.acmapis.commercial_management_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Model class representing a sale for data transfer operations.
 * This model is used for API communication and contains the same attributes as SaleEntity.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleModel implements Serializable {
    /**
     * Unique identifier for the sale.
     */
    private UUID saleId;

    /**
     * Date and time when the sale was made.
     */
    private LocalDateTime saleDate;

    /**
     * Total amount of the sale transaction.
     */
    private Long totalAmount;

    /**
     * User who made the purchase.
     */
    private UserModel user;
}