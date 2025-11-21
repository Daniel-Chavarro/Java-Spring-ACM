package org.acmapis.commercial_management_system.controller;

import org.acmapis.commercial_management_system.model.dto.SaleProductModel;
import org.acmapis.commercial_management_system.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST Controller for SaleProduct management operations.
 * Provides endpoints for CRUD operations on sale-product relationships.
 * 
 * Note: Custom query endpoints for analytics are handled by ProductController as requested.
 *
 * @author Commercial Management System Team
 * @version 1.0
 * @since 2025-11-21
 */
@RestController
@RequestMapping("/api/v1/sale-products")
@CrossOrigin(origins = "*")
public class SaleProductController {

    private final SaleProductService saleProductService;

    @Autowired
    public SaleProductController(SaleProductService saleProductService) {
        this.saleProductService = saleProductService;
    }

    /**
     * Get all sale-product relationships in the system.
     *
     * @return List of all sale-product relationships
     */
    @GetMapping
    public ResponseEntity<List<SaleProductModel>> getAllSaleProducts() {
        List<SaleProductModel> saleProducts = saleProductService.getAllSaleProducts();
        return ResponseEntity.ok(saleProducts);
    }

    /**
     * Get a specific sale-product relationship by ID.
     *
     * @param id The sale-product relationship ID
     * @return SaleProduct if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<SaleProductModel> getSaleProductById(@PathVariable Long id) {
        Optional<SaleProductModel> saleProduct = saleProductService.getSaleProductById(id);
        return saleProduct.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new sale-product relationship.
     *
     * @param saleProduct SaleProduct data to create
     * @return Created sale-product relationship with 201 status
     */
    @PostMapping
    public ResponseEntity<SaleProductModel> createSaleProduct(@RequestBody SaleProductModel saleProduct) {
        SaleProductModel savedSaleProduct = saleProductService.saveSaleProduct(saleProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSaleProduct);
    }

    /**
     * Update an existing sale-product relationship.
     *
     * @param id The sale-product relationship ID to update
     * @param saleProduct Updated sale-product data
     * @return Updated sale-product relationship
     */
    @PutMapping("/{id}")
    public ResponseEntity<SaleProductModel> updateSaleProduct(@PathVariable Long id, @RequestBody SaleProductModel saleProduct) {
        saleProduct.setSaleProductId(id);
        SaleProductModel updatedSaleProduct = saleProductService.updateSaleProduct(id,saleProduct);
        return ResponseEntity.ok(updatedSaleProduct);
    }

    /**
     * Delete a sale-product relationship by ID.
     *
     * @param id The sale-product relationship ID to delete
     * @return 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleProduct(@PathVariable Long id) {
        saleProductService.deleteSaleProductById(id);
        return ResponseEntity.noContent().build();
    }
}