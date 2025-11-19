package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface UserRepository extends JpaRepository<UserEntity, UUID> {

    List<UserEntity> findByLastNameIgnoreCase(String lastName);

    List<UserEntity> findByCity_CityId(Long cityCityId);

    List<UserEntity> findByCity_CityName(String cityCityName);

    List<UserEntity> findByCity_Department_DepartmentName(String cityDepartmentDepartmentName);

    List<UserEntity> findByFirstNameLikeIgnoreCase(String firstName);
}
