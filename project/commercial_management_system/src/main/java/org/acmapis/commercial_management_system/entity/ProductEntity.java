package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Entity representing a product in the commercial management system.
 * Products are the main items for sale and can be associated with multiple categories,
 * stored in different stores, and included in sales transactions.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    /**
     * Unique identifier for the product.
     * Auto-generated using UUID strategy for global uniqueness.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID productId;

    /**
     * Name of the product.
     * Cannot be null and has a maximum length of 128 characters.
     */
    @Column(name = "product_name", nullable = false, length = 128)
    private String productName;

    /**
     * Detailed description of the product.
     * Cannot be null.
     */
    @Column(name = "description", nullable = false)
    private String productDescription;

    /**
     * Price of the product in the system's default currency.
     * Cannot be null.
     */
    @Column(name = "price", nullable = false)
    private Double price;

    /**
     * Timestamp when the product was created.
     * Automatically set on entity creation and never updated.
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the product was last updated.
     * Automatically updated on every entity modification.
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Many-to-many relationship with CategoryEntity.
     * A product can belong to multiple categories.
     * Uses a join table 'product_category' to manage the relationship.
     * Cascade type is set to MERGE to propagate merge operations.
     * Fetch type is set to LAZY to optimize performance.
     */
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "category_id_fk")
    )
    private List<CategoryEntity> categories;
}
