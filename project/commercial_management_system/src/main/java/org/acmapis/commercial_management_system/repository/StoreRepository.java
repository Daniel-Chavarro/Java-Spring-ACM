package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.acmapis.commercial_management_system.entity.StoreEntity;
import org.acmapis.commercial_management_system.entity.StoreProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing StoreEntity data access operations.
 * Provides CRUD operations and queries for store management and product inventory.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {

    /**
     * Finds all stores located in a specific city by city ID.
     *
     * @param cityCityId The unique identifier of the city
     * @return List of StoreEntity objects located in the specified city
     */
    List<StoreEntity> findByCity_CityId(Long cityCityId);

    /**
     * Finds all stores located in a specific city by city name.
     *
     * @param cityCityName The name of the city
     * @return List of StoreEntity objects located in the city with the specified name
     */
    List<StoreEntity> findByCity_CityName(String cityCityName);

    /**
     * Retrieves all products available in a specific store by store ID.
     *
     * @param storeId The unique identifier of the store
     * @return List of ProductEntity objects available in the specified store
     */
    @Query("SELECT sp.product FROM StoreProductEntity sp WHERE sp.store.storeId = :storeId")
    List<ProductEntity> findProductsByStoreId(@Param("storeId") UUID storeId);

    /**
     * Retrieves all products available in a specific store by store name.
     *
     * @param storeName The name of the store
     * @return List of ProductEntity objects available in the store with the specified name
     */
    @Query("SELECT sp.product FROM StoreProductEntity sp WHERE sp.store.storeName = :storeName")
    List<ProductEntity> findProductByStoreName(String storeName);

    /**
     * Retrieves all store-product relationships for a specific store.
     * This includes inventory details like stock levels and storage addresses.
     *
     * @param storeId The unique identifier of the store
     * @return List of StoreProductEntity objects representing the inventory for the specified store
     */
    @Query("SELECT sp FROM StoreProductEntity sp WHERE sp.store.storeId = :storeId")
    List<StoreProductEntity> findStoreProductsByStoreId(@Param("storeId") UUID storeId);

}
