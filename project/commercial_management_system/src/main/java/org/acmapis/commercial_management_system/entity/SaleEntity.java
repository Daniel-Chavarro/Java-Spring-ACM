package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a sale transaction in the commercial management system.
 * Sales capture transaction details including date, total amount, and the associated user.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Entity
@Table(name = "sale")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleEntity {
    /**
     * Unique identifier for the sale.
     * Auto-generated using UUID strategy for global uniqueness.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "sale_id")
    private UUID saleId;

    /**
     * Date and time when the sale was made.
     * Automatically set when the entity is created.
     */
    @Column(name = "sale_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime saleDate;

    /**
     * Total amount of the sale transaction.
     * Stored as long value representing the amount in the smallest currency unit.
     */
    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    /**
     * User who made the purchase.
     * Represents a many-to-one relationship with the user entity.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id_fk", nullable = false)
    private UserEntity user;
}
