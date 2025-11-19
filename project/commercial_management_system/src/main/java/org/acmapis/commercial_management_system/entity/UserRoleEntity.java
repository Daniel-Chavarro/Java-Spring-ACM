package org.acmapis.commercial_management_system.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acmapis.commercial_management_system.model.enums.UserRole;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
}
