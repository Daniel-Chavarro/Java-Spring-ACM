package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "store_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_product_id")
    private Long id;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "store_id_fk", nullable = false)
    private StoreEntity store;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id_fk", nullable = false)
    private ProductEntity product;
}
