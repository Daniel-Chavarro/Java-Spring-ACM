package org.acmapis.commercial_management_system.controller;

import org.acmapis.commercial_management_system.model.dto.ProductModel;
import org.acmapis.commercial_management_system.model.dto.StoreModel;
import org.acmapis.commercial_management_system.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST Controller for Store management operations.
 * Provides endpoints for CRUD operations and store-related queries.
 *
 * @author Commercial Management System Team
 * @version 1.0
 * @since 2025-11-21
 */
@RestController
@RequestMapping("/api/v1/stores")
@CrossOrigin(origins = "*")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Get all stores in the system.
     *
     * @return List of all stores
     */
    @GetMapping
    public ResponseEntity<List<StoreModel>> getAllStores() {
        List<StoreModel> stores = storeService.getAllStores();
        return ResponseEntity.ok(stores);
    }

    /**
     * Get a specific store by ID.
     *
     * @param id The store ID
     * @return Store if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<StoreModel> getStoreById(@PathVariable UUID id) {
        Optional<StoreModel> store = storeService.getStoreById(id);
        return store.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new store.
     *
     * @param store Store data to create
     * @return Created store with 201 status
     */
    @PostMapping
    public ResponseEntity<StoreModel> createStore(@RequestBody StoreModel store) {
        StoreModel savedStore = storeService.saveStore(store);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStore);
    }

    /**
     * Update an existing store.
     *
     * @param id    The store ID to update
     * @param store Updated store data
     * @return Updated store
     */
    @PutMapping("/{id}")
    public ResponseEntity<StoreModel> updateStore(@PathVariable UUID id, @RequestBody StoreModel store) {
        store.setStoreId(id);
        StoreModel updatedStore = storeService.updateStore(id, store);
        return ResponseEntity.ok(updatedStore);
    }

    /**
     * Delete a store by ID.
     *
     * @param id The store ID to delete
     * @return 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable UUID id) {
        storeService.deleteStoreById(id);
        return ResponseEntity.noContent().build();
    }

    // === Store Query Endpoints ===

    /**
     * Find stores by city ID.
     *
     * @param cityId The city ID to search for
     * @return List of stores in the specified city
     */
    @GetMapping("/search/by-city-id")
    public ResponseEntity<List<StoreModel>> findByCityId(@RequestParam Long cityId) {
        List<StoreModel> stores = storeService.getStoresByCityId(cityId);
        return ResponseEntity.ok(stores);
    }

    /**
     * Find stores by city name.
     *
     * @param cityName The city name to search for
     * @return List of stores in the specified city
     */
    @GetMapping("/search/by-city-name")
    public ResponseEntity<List<StoreModel>> findByCityName(@RequestParam String cityName) {
        List<StoreModel> stores = storeService.getStoresByCityName(cityName);
        return ResponseEntity.ok(stores);
    }

    /**
     * Get all products available in a specific store.
     *
     * @param id The store ID
     * @return List of products available in the store
     */
    @GetMapping("/search/products/by-store-id/{id}")
    public ResponseEntity<List<ProductModel>> getProductsByStoreId(@PathVariable UUID id) {
        List<ProductModel> products = storeService.getProductsByStoreId(id);
        return ResponseEntity.ok(products);
    }

    /**
     * Get all products available in a specific store by store name.
     *
     * @param storeName The store name
     * @return List of products available in the store
     */
    @GetMapping("/search/products/by-store-name/{storeName}")
    public ResponseEntity<List<ProductModel>> getProductsByStoreName(@PathVariable String storeName) {
        List<ProductModel> products = storeService.getProductsByStoreName(storeName);
        return ResponseEntity.ok(products);
    }
}