package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "sale")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "sale_id")
    private Long saleId;

    @Column(name = "sale_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime saleDate;

    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id_fk", nullable = false)
    private UserEntity user;
}
