package org.acmapis.commercial_management_system.controller;

import org.acmapis.commercial_management_system.model.dto.SaleModel;
import org.acmapis.commercial_management_system.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST Controller for Sale management operations.
 * Provides endpoints for CRUD operations and sales analytics.
 *
 * @author Commercial Management System Team
 * @version 1.0
 * @since 2025-11-21
 */
@RestController
@RequestMapping("/api/v1/sales")
@CrossOrigin(origins = "*")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    /**
     * Get all sales in the system.
     *
     * @return List of all sales
     */
    @GetMapping
    public ResponseEntity<List<SaleModel>> getAllSales() {
        List<SaleModel> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    /**
     * Get a specific sale by ID.
     *
     * @param id The sale ID
     * @return Sale if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<SaleModel> getSaleById(@PathVariable UUID id) {
        Optional<SaleModel> sale = saleService.getSaleById(id);
        return sale.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new sale.
     *
     * @param sale Sale data to create
     * @return Created sale with 201 status
     */
    @PostMapping
    public ResponseEntity<SaleModel> createSale(@RequestBody SaleModel sale) {
        SaleModel savedSale = saleService.saveSale(sale);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSale);
    }

    /**
     * Update an existing sale.
     *
     * @param id   The sale ID to update
     * @param sale Updated sale data
     * @return Updated sale
     */
    @PutMapping("/{id}")
    public ResponseEntity<SaleModel> updateSale(@PathVariable UUID id, @RequestBody SaleModel sale) {
        sale.setSaleId(id);
        SaleModel updatedSale = saleService.updateSale(id, sale);
        return ResponseEntity.ok(updatedSale);
    }

    /**
     * Delete a sale by ID.
     *
     * @param id The sale ID to delete
     * @return 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable UUID id) {
        saleService.deleteSaleById(id);
        return ResponseEntity.noContent().build();
    }

    // === Sale Query Endpoints ===

    /**
     * Find sales by user ID.
     *
     * @param userId The user ID to search for
     * @return List of sales made by the specified user
     */
    @GetMapping("/search/by-user-id")
    public ResponseEntity<List<SaleModel>> findByUserUserId(@RequestParam UUID userId) {
        List<SaleModel> sales = saleService.getSalesByUserId(userId);
        return ResponseEntity.ok(sales);
    }

    /**
     * Find sales by user's first name.
     *
     * @param firstName The user's first name to search for
     * @return List of sales made by users with the specified first name
     */
    @GetMapping("/search/by-user-firstname")
    public ResponseEntity<List<SaleModel>> findByUserFirstName(@RequestParam String firstName) {
        List<SaleModel> sales = saleService.getSalesByUserFirstName(firstName);
        return ResponseEntity.ok(sales);
    }

    /**
         * Find sales with total amount greater than the specified value.
     *
     * @param amount The minimum total amount
     * @return List of sales with total amount greater than the specified value
     */
    @GetMapping("/search/by-min-total-amount")
    public ResponseEntity<List<SaleModel>> findByTotalAmountAfter(@RequestParam Long amount) {
        List<SaleModel> sales = saleService.getSalesWithAmountGreaterThan(amount);
        return ResponseEntity.ok(sales);
    }

    // === Sales Analytics Endpoints ===

    /**
     * Get total sales amount for a specific date.
     *
     * @param date The date to calculate totals for (format: YYYY-MM-DD)
     * @return Total amount of sales for the specified date
     */
    @GetMapping("/analytics/total-by-date")
    public ResponseEntity<Double> getTotalAmountBySaleDate(@RequestParam String date) {
        LocalDateTime saleDate = LocalDateTime.parse(date);
        Long totalAmount = saleService.getTotalSalesAmountByDate(saleDate);
        return ResponseEntity.ok(totalAmount != null ? totalAmount : 0.0);
    }
}