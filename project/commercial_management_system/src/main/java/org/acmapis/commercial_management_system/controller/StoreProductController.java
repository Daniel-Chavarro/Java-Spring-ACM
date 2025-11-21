package org.acmapis.commercial_management_system.controller;

import org.acmapis.commercial_management_system.model.dto.StoreProductModel;
import org.acmapis.commercial_management_system.service.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for StoreProduct management operations.
 * Provides endpoints for CRUD operations on store-product relationships.
 * <p>
 * Note: Custom query endpoints are handled by ProductController as requested.
 *
 * @author Commercial Management System Team
 * @version 1.0
 * @since 2025-11-21
 */
@RestController
@RequestMapping("/api/v1/store-products")
@CrossOrigin(origins = "*")
public class StoreProductController {

    private final StoreProductService storeProductService;

    @Autowired
    public StoreProductController(StoreProductService storeProductService) {
        this.storeProductService = storeProductService;
    }

    /**
     * Get all store-product relationships in the system.
     *
     * @return List of all store-product relationships
     */
    @GetMapping
    public ResponseEntity<List<StoreProductModel>> getAllStoreProducts() {
        List<StoreProductModel> storeProducts = storeProductService.getAllStoreProducts();
        return ResponseEntity.ok(storeProducts);
    }

    /**
     * Get a specific store-product relationship by ID.
     *
     * @param id The store-product relationship ID
     * @return StoreProduct if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<StoreProductModel> getStoreProductById(@PathVariable Long id) {
        Optional<StoreProductModel> storeProduct = storeProductService.getStoreProductById(id);
        return storeProduct.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new store-product relationship.
     *
     * @param storeProduct StoreProduct data to create
     * @return Created store-product relationship with 201 status
     */
    @PostMapping
    public ResponseEntity<StoreProductModel> createStoreProduct(@RequestBody StoreProductModel storeProduct) {
        StoreProductModel savedStoreProduct = storeProductService.saveStoreProduct(storeProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStoreProduct);
    }

    /**
     * Update an existing store-product relationship.
     *
     * @param id           The store-product relationship ID to update
     * @param storeProduct Updated store-product data
     * @return Updated store-product relationship
     */
    @PutMapping("/{id}")
    public ResponseEntity<StoreProductModel> updateStoreProduct(@PathVariable Long id, @RequestBody StoreProductModel storeProduct) {
        storeProduct.setId(id);
        StoreProductModel updatedStoreProduct = storeProductService.updateStoreProduct(id, storeProduct);
        return ResponseEntity.ok(updatedStoreProduct);
    }

    /**
     * Delete a store-product relationship by ID.
     *
     * @param id The store-product relationship ID to delete
     * @return 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStoreProduct(@PathVariable Long id) {
        storeProductService.deleteStoreProductById(id);
        return ResponseEntity.noContent().build();
    }
}