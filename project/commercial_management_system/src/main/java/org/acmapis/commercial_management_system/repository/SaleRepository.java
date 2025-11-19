package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.SaleEntity;
import org.acmapis.commercial_management_system.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing SaleEntity data access operations.
 * Provides CRUD operations and analytics queries for sales transactions.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, UUID> {
    /**
     * Finds all sales made by a specific user using their user ID.
     *
     * @param userUserId The unique identifier of the user
     * @return List of SaleEntity objects associated with the specified user
     */
    List<SaleEntity> findByUser_UserId(UUID userUserId);

    /**
     * Finds all sales made by a specific user entity.
     *
     * @param user The UserEntity object
     * @return List of SaleEntity objects associated with the specified user
     */
    List<SaleEntity> findByUser_(UserEntity user);

    /**
     * Finds all sales made by users with the specified first name.
     *
     * @param userFirstName The first name of the user
     * @return List of SaleEntity objects made by users with the specified first name
     */
    List<SaleEntity> findByUser_FirstName(String userFirstName);

    /**
     * Calculates the total sales amount for a specific date.
     *
     * @param saleDate The date to calculate total sales for
     * @return The sum of all sale amounts for the specified date, or null if no sales found
     */
    @Query("SELECT SUM(s.totalAmount) FROM SaleEntity s WHERE s.saleDate = ?1")
    Long sumTotalAmountBySaleDate(LocalDateTime saleDate);

    /**
     * Finds all sales with total amount greater than the specified threshold.
     *
     * @param totalAmountAfter The minimum total amount threshold
     * @return List of SaleEntity objects with total amount greater than the specified value
     */
    List<SaleEntity> findByTotalAmountAfter(Long totalAmountAfter);


}
