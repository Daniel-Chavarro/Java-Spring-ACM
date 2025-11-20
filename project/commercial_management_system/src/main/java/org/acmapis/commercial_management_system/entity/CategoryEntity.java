package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Entity representing a product category in the commercial management system.
 * This entity manages product categorization and maintains many-to-many relationships with products.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    /**
     * Unique identifier for the category.
     * Auto-generated using database identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * Name of the category.
     * Cannot be null and has a maximum length of 64 characters.
     */
    @Column(name = "category_name", nullable = false, length = 64, unique = true)
    private String categoryName;

    /**
     * List of products associated with this category.
     * Represents a many-to-many relationship managed through the product_category junction table.
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "category_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "product_id_fk")
    )
    private List<ProductEntity> products;
}
