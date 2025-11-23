package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing UserRoleEntity data access operations.
 * Provides CRUD operations for user role management and role-based queries.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-19
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

}
