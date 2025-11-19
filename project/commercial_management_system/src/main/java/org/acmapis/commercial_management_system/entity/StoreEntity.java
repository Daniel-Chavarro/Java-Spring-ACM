package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "store")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false, length = 64)
    private String storeName;

    @ManyToOne
    @JoinColumn(name = "city_id_fk", nullable = false)
    private CityEntity city;
}
