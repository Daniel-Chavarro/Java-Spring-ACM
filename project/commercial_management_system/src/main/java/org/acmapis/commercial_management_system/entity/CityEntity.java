package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "city")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @Column(nullable = false, length = 64)
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "department_id_fk", nullable = false)
    private DepartmentEntity department;
}
