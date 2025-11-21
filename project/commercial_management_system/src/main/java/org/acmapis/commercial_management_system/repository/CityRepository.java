package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing CityEntity data access operations.
 * Provides standard CRUD operations for city-related database interactions.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

}
