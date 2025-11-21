package org.acmapis.commercial_management_system.service;

import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.acmapis.commercial_management_system.entity.StoreEntity;
import org.acmapis.commercial_management_system.entity.StoreProductEntity;
import org.acmapis.commercial_management_system.model.dto.ProductModel;
import org.acmapis.commercial_management_system.model.dto.StoreModel;
import org.acmapis.commercial_management_system.model.dto.StoreProductModel;
import org.acmapis.commercial_management_system.repository.StoreRepository;
import org.acmapis.commercial_management_system.utils.mapper.ProductMapper;
import org.acmapis.commercial_management_system.utils.mapper.StoreMapper;
import org.acmapis.commercial_management_system.utils.mapper.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing store-related business logic.
 * Provides CRUD operations and inventory management for stores.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Service
public class StoreService {

    /**
     * Repository interface for accessing store data in the database.
     * Provides CRUD operations and inventory management queries for store entities.
     */
    private final StoreRepository storeRepository;

    /**
     * Mapper interface for converting between StoreEntity and StoreModel objects.
     * Handles automatic mapping using MapStruct framework.
     */
    private final StoreMapper storeMapper;

    /**
     * Mapper interface for converting between ProductEntity and ProductModel objects.
     * Used for mapping product data in inventory operations.
     */
    private final ProductMapper productMapper;

    /**
     * Mapper interface for converting between StoreProductEntity and StoreProductModel objects.
     * Used for mapping store-product relationship data in inventory management.
     */
    private final StoreProductMapper storeProductMapper;

    /**
     * Constructs a new StoreService with the required dependencies.
     * Uses constructor-based dependency injection for better testability and immutability.
     *
     * @param storeRepository    the repository for store data access operations
     * @param storeMapper        the mapper for store entity-model conversions
     * @param productMapper      the mapper for product entity-model conversions
     * @param storeProductMapper the mapper for store-product relationship conversions
     */
    @Autowired
    public StoreService(StoreRepository storeRepository,
                        StoreMapper storeMapper,
                        ProductMapper productMapper,
                        StoreProductMapper storeProductMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
        this.productMapper = productMapper;
        this.storeProductMapper = storeProductMapper;
    }

    /**
     * Retrieves all stores from the database.
     *
     * @return List of StoreModel objects representing all stores
     */
    public List<StoreModel> getAllStores() {
        List<StoreEntity> entities = storeRepository.findAll();
        return storeMapper.toModelList(entities);
    }

    /**
     * Retrieves a store by its unique identifier.
     *
     * @param storeId The unique identifier of the store
     * @return Optional containing the StoreModel if found, empty otherwise
     */
    public Optional<StoreModel> getStoreById(UUID storeId) {
        Optional<StoreEntity> entity = storeRepository.findById(storeId);
        return entity.map(storeMapper::toModel);
    }

    /**
     * Creates a new store in the database.
     *
     * @param storeModel The StoreModel containing the store data to save
     * @return The saved StoreModel with generated ID
     */
    public StoreModel saveStore(StoreModel storeModel) {
        StoreEntity entity = storeMapper.toEntity(storeModel);
        StoreEntity savedEntity = storeRepository.save(entity);
        return storeMapper.toModel(savedEntity);
    }

    /**
     * Updates an existing store in the database.
     * Uses the find-modify-save pattern to ensure data integrity and prevent ID conflicts.
     *
     * @param storeId    The unique identifier of the store to update
     * @param storeModel The StoreModel containing the updated store data
     * @return The updated StoreModel
     * @throws RuntimeException if the store with the given ID is not found
     */
    public StoreModel updateStore(UUID storeId, StoreModel storeModel) {
        StoreEntity existingEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found with ID: " + storeId));

        storeMapper.updateEntityFromModel(storeModel, existingEntity);

        StoreEntity updatedEntity = storeRepository.save(existingEntity);
        return storeMapper.toModel(updatedEntity);
    }

    /**
     * Deletes a store by its unique identifier.
     *
     * @param storeId The unique identifier of the store to delete
     */
    public void deleteStoreById(UUID storeId) {
        storeRepository.deleteById(storeId);
    }

    // Custom query methods

    /**
     * Finds all stores located in a specific city by city ID.
     *
     * @param cityId The unique identifier of the city
     * @return List of StoreModel objects located in the specified city
     */
    public List<StoreModel> getStoresByCityId(Long cityId) {
        List<StoreEntity> entities = storeRepository.findByCity_CityId(cityId);
        return storeMapper.toModelList(entities);
    }

    /**
     * Finds all stores located in a specific city by city name.
     *
     * @param cityName The name of the city
     * @return List of StoreModel objects located in the city with the specified name
     */
    public List<StoreModel> getStoresByCityName(String cityName) {
        List<StoreEntity> entities = storeRepository.findByCity_CityName(cityName);
        return storeMapper.toModelList(entities);
    }

    /**
     * Retrieves all products available in a specific store by store ID.
     *
     * @param storeId The unique identifier of the store
     * @return List of ProductModel objects available in the specified store
     */
    public List<ProductModel> getProductsByStoreId(UUID storeId) {
        List<ProductEntity> entities = storeRepository.findProductsByStoreId(storeId);
        return productMapper.toModelList(entities);
    }

    /**
     * Retrieves all products available in a specific store by store name.
     *
     * @param storeName The name of the store
     * @return List of ProductModel objects available in the store with the specified name
     */
    public List<ProductModel> getProductsByStoreName(String storeName) {
        List<ProductEntity> entities = storeRepository.findProductByStoreName(storeName);
        return productMapper.toModelList(entities);
    }

    /**
     * Retrieves all store-product relationships for a specific store.
     * This includes inventory details like stock levels and storage addresses.
     *
     * @param storeId The unique identifier of the store
     * @return List of StoreProductModel objects representing the inventory for the specified store
     */
    public List<StoreProductModel> getStoreProductsByStoreId(UUID storeId) {
        List<StoreProductEntity> entities = storeRepository.findStoreProductsByStoreId(storeId);
        return storeProductMapper.toModelList(entities);
    }
}
