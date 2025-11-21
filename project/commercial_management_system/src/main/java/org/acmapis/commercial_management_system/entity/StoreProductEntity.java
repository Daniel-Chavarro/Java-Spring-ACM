package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing the inventory relationship between stores and products.
 * This entity manages stock levels and location information for products in specific stores.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Entity
@Table(name = "store_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreProductEntity {
    /**
     * Unique identifier for the store-product relationship.
     * Auto-generated using database identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_product_id")
    private Long id;

    /**
     * Current stock quantity of the product in this store.
     * Cannot be null.
     */
    @Column(name = "stock", nullable = false)
    private Long stock;

    /**
     * Physical address or location within the store where the product is stored.
     * Cannot be null.
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Store where the product is located.
     * Represents a many-to-one relationship with the store entity.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "store_id_fk", nullable = false)
    private StoreEntity store;

    /**
     * Product that is stored in this store.
     * Represents a many-to-one relationship with the product entity.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id_fk", nullable = false)
    private ProductEntity product;
}
