package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "store")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "store_id")
    private UUID storeId;

    @Column(name = "store_name", nullable = false, length = 64)
    private String storeName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id_fk", nullable = false)
    private CityEntity city;
}
