package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.StoreProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing StoreProductEntity data access operations.
 * Provides CRUD operations for managing inventory relationships between stores and products.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Repository
public interface StoreProductRepository extends JpaRepository<StoreProductEntity, Long> {

}
