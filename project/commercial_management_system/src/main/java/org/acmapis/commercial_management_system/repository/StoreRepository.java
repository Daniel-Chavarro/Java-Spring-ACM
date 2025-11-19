package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.acmapis.commercial_management_system.entity.StoreEntity;
import org.acmapis.commercial_management_system.entity.StoreProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {

    List<StoreEntity> findByCity_CityId(Long cityCityId);

    List<StoreEntity> findByCity_CityName(String cityCityName);

    @Query("SELECT sp.product FROM StoreProductEntity sp WHERE sp.store.storeId = :storeId")
    List<ProductEntity> findProductsByStoreId(@Param("storeId") UUID storeId);

    @Query("SELECT sp.product FROM StoreProductEntity sp WHERE sp.store.storeName = :storeName")
    List<ProductEntity> findProductByStoreName(String storeName);

    @Query("SELECT sp FROM StoreProductEntity sp WHERE sp.store.storeId = :storeId")
    List<StoreProductEntity> findStoreProductsByStoreId(@Param("storeId") UUID storeId);

}
