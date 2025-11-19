package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sale_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_product_id")
    private Long saleProductId;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sale_id_fk", nullable = false)
    private SaleEntity sale;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id_fk", nullable = false)
    private ProductEntity product;

}
