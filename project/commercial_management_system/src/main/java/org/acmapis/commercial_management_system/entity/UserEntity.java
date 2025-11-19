package org.acmapis.commercial_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 32)
    private String firstName;

    @Column(nullable = false, length = 32)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, length = 10)
    private String phone;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id_fk", nullable = false)
    private UserRoleEntity role;

    @ManyToOne
    @JoinColumn(name = "city_id_fk", nullable = false)
    private CityEntity city;
}
