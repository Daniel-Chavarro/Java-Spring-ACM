package org.acmapis.commercial_management_system.service;

import org.acmapis.commercial_management_system.entity.SaleEntity;
import org.acmapis.commercial_management_system.model.dto.SaleModel;
import org.acmapis.commercial_management_system.repository.SaleRepository;
import org.acmapis.commercial_management_system.utils.mapper.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing sale-related business logic.
 * Provides CRUD operations and analytics queries for sales transactions.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Service
public class SaleService {

    /**
     * Repository interface for accessing sale data in the database.
     * Provides CRUD operations and custom queries for sale entities.
     */
    private final SaleRepository saleRepository;

    /**
     * Mapper interface for converting between SaleEntity and SaleModel objects.
     * Handles automatic mapping using MapStruct framework.
     */
    private final SaleMapper saleMapper;

    /**
     * Constructs a new SaleService with the required dependencies.
     * Uses constructor-based dependency injection for better testability and immutability.
     *
     * @param saleRepository the repository for sale data access operations
     * @param saleMapper the mapper for entity-model conversions
     */
    @Autowired
    public SaleService(SaleRepository saleRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    /**
     * Retrieves all sales from the database.
     *
     * @return List of SaleModel objects representing all sales
     */
    public List<SaleModel> getAllSales() {
        List<SaleEntity> entities = saleRepository.findAll();
        return saleMapper.toModelList(entities);
    }

    /**
     * Retrieves a sale by its unique identifier.
     *
     * @param saleId The unique identifier of the sale
     * @return Optional containing the SaleModel if found, empty otherwise
     */
    public Optional<SaleModel> getSaleById(UUID saleId) {
        Optional<SaleEntity> entity = saleRepository.findById(saleId);
        return entity.map(saleMapper::toModel);
    }

    /**
     * Creates a new sale in the database.
     *
     * @param saleModel The SaleModel containing the sale data to save
     * @return The saved SaleModel with generated ID
     */
    public SaleModel saveSale(SaleModel saleModel) {
        SaleEntity entity = saleMapper.toEntity(saleModel);
        SaleEntity savedEntity = saleRepository.save(entity);
        return saleMapper.toModel(savedEntity);
    }

    /**
     * Updates an existing sale in the database.
     * Uses the find-modify-save pattern to ensure data integrity and prevent ID conflicts.
     *
     * @param saleId The unique identifier of the sale to update
     * @param saleModel The SaleModel containing the updated sale data
     * @return The updated SaleModel
     * @throws RuntimeException if the sale with the given ID is not found
     */
    public SaleModel updateSale(UUID saleId, SaleModel saleModel) {
        SaleEntity existingEntity = saleRepository.findById(saleId)
            .orElseThrow(() -> new RuntimeException("Sale not found with ID: " + saleId));

        saleMapper.updateEntityFromModel(saleModel, existingEntity);

        SaleEntity updatedEntity = saleRepository.save(existingEntity);
        return saleMapper.toModel(updatedEntity);
    }

    /**
     * Deletes a sale by its unique identifier.
     *
     * @param saleId The unique identifier of the sale to delete
     */
    public void deleteSaleById(UUID saleId) {
        saleRepository.deleteById(saleId);
    }

    // Custom query methods

    /**
     * Finds all sales made by a specific user using their user ID.
     *
     * @param userId The unique identifier of the user
     * @return List of SaleModel objects associated with the specified user
     */
    public List<SaleModel> getSalesByUserId(UUID userId) {
        List<SaleEntity> entities = saleRepository.findByUser_UserId(userId);
        return saleMapper.toModelList(entities);
    }

    /**
     * Finds all sales made by users with the specified first name.
     *
     * @param firstName The first name of the user
     * @return List of SaleModel objects made by users with the specified first name
     */
    public List<SaleModel> getSalesByUserFirstName(String firstName) {
        List<SaleEntity> entities = saleRepository.findByUser_FirstName(firstName);
        return saleMapper.toModelList(entities);
    }

    /**
     * Calculates the total sales amount for a specific date.
     *
     * @param saleDate The date to calculate total sales for
     * @return The sum of all sale amounts for the specified date, or null if no sales found
     */
    public Long getTotalSalesAmountByDate(LocalDateTime saleDate) {
        return saleRepository.sumTotalAmountBySaleDate(saleDate);
    }

    /**
     * Finds all sales with total amount greater than the specified threshold.
     *
     * @param minAmount The minimum total amount threshold
     * @return List of SaleModel objects with total amount greater than the specified value
     */
    public List<SaleModel> getSalesWithAmountGreaterThan(Long minAmount) {
        List<SaleEntity> entities = saleRepository.findByTotalAmountAfter(minAmount);
        return saleMapper.toModelList(entities);
    }
}
