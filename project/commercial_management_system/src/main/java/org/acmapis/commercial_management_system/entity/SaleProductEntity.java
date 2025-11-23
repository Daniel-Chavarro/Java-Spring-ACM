package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing the junction table between sales and products.
 * This entity manages the many-to-many relationship between sales and products,
 * including quantity and price information for each product in a sale.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Entity
@Table(name = "sale_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleProductEntity {
    /**
     * Unique identifier for the sale-product relationship.
     * Auto-generated using database identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_product_id")
    private Long saleProductId;

    /**
     * Quantity of the product purchased in this sale.
     * Cannot be null.
     */
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    /**
     * Sale transaction that includes this product.
     * Represents the many-to-one relationship with the sale entity.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sale_id_fk", nullable = false)
    private SaleEntity sale;

    /**
     * Product that was sold in this transaction.
     * Represents the many-to-one relationship with the product entity.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id_fk", nullable = false)
    private ProductEntity product;

}
