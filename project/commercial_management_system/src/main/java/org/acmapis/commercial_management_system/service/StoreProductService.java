package org.acmapis.commercial_management_system.service;

import org.acmapis.commercial_management_system.entity.StoreProductEntity;
import org.acmapis.commercial_management_system.model.dto.StoreProductModel;
import org.acmapis.commercial_management_system.repository.StoreProductRepository;
import org.acmapis.commercial_management_system.utils.mapper.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing store-product relationship business logic.
 * Provides CRUD operations for managing inventory relationships between stores and products.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Service
public class StoreProductService {

    private final StoreProductRepository storeProductRepository;
    private final StoreProductMapper storeProductMapper;

    @Autowired
    public StoreProductService(StoreProductRepository storeProductRepository,
                              StoreProductMapper storeProductMapper) {
        this.storeProductRepository = storeProductRepository;
        this.storeProductMapper = storeProductMapper;
    }

    /**
     * Retrieves all store-product relationships from the database.
     *
     * @return List of StoreProductModel objects representing all store-product relationships
     */
    public List<StoreProductModel> getAllStoreProducts() {
        List<StoreProductEntity> entities = storeProductRepository.findAll();
        return storeProductMapper.toModelList(entities);
    }

    /**
     * Retrieves a store-product relationship by its unique identifier.
     *
     * @param storeProductId The unique identifier of the store-product relationship
     * @return Optional containing the StoreProductModel if found, empty otherwise
     */
    public Optional<StoreProductModel> getStoreProductById(Long storeProductId) {
        Optional<StoreProductEntity> entity = storeProductRepository.findById(storeProductId);
        return entity.map(storeProductMapper::toModel);
    }

    /**
     * Creates a new store-product relationship in the database.
     *
     * @param storeProductModel The StoreProductModel containing the relationship data to save
     * @return The saved StoreProductModel with generated ID
     */
    public StoreProductModel saveStoreProduct(StoreProductModel storeProductModel) {
        StoreProductEntity entity = storeProductMapper.toEntity(storeProductModel);
        StoreProductEntity savedEntity = storeProductRepository.save(entity);
        return storeProductMapper.toModel(savedEntity);
    }

    /**
     * Updates an existing store-product relationship in the database.
     *
     * @param storeProductModel The StoreProductModel containing the updated relationship data
     * @return The updated StoreProductModel
     */
    public StoreProductModel updateStoreProduct(StoreProductModel storeProductModel) {
        StoreProductEntity entity = storeProductMapper.toEntity(storeProductModel);
        StoreProductEntity updatedEntity = storeProductRepository.save(entity);
        return storeProductMapper.toModel(updatedEntity);
    }

    /**
     * Deletes a store-product relationship by its unique identifier.
     *
     * @param storeProductId The unique identifier of the store-product relationship to delete
     */
    public void deleteStoreProductById(Long storeProductId) {
        storeProductRepository.deleteById(storeProductId);
    }
}
