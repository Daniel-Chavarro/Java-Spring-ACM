package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.SaleEntity;
import org.acmapis.commercial_management_system.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, UUID> {
    List<SaleEntity> findByUser_UserId(UUID userUserId);

    List<SaleEntity> findByUser_(UserEntity user);

    List<SaleEntity> findByUser_FirstName(String userFirstName);

    @Query("SELECT SUM(s.totalAmount) FROM SaleEntity s WHERE s.saleDate = ?1")
    Long sumTotalAmountBySaleDate(LocalDateTime saleDate);

    List<SaleEntity> findByTotalAmountAfter(Long totalAmountAfter);


}
