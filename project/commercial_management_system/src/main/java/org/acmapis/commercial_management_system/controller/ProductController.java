package org.acmapis.commercial_management_system.controller;

import org.acmapis.commercial_management_system.model.dto.ProductModel;
import org.acmapis.commercial_management_system.service.ProductService;
import org.acmapis.commercial_management_system.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST Controller for Product management operations.
 * Provides endpoints for CRUD operations, product queries, and analytics.
 *
 * @author Commercial Management System Team
 * @version 1.0
 * @since 2025-11-21
 */
@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Get all products in the system.
     *
     * @return List of all products
     */
    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Get a specific product by ID.
     *
     * @param id The product ID
     * @return Product if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable UUID id) {
        Optional<ProductModel> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new product.
     *
     * @param product Product data to create
     * @return Created product with 201 status
     */
    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
        ProductModel savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    /**
     * Update an existing product.
     *
     * @param id      The product ID to update
     * @param product Updated product data
     * @return Updated product
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable UUID id, @RequestBody ProductModel product) {
        product.setProductId(id);
        ProductModel updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Delete a product by ID.
     *
     * @param id The product ID to delete
     * @return 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    // === Product Query Endpoints ===

    /**
     * Get products within a specific price range.
     *
     * @param minPrice Minimum price (inclusive)
     * @param maxPrice Maximum price (inclusive)
     * @return List of products within the price range
     */
    @GetMapping("/search/by-price-range")
    public ResponseEntity<List<ProductModel>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<ProductModel> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    /**
     * Get products ordered by price in ascending order.
     *
     * @return List of products sorted by price (low to high)
     */
    @GetMapping("/search/sorted-by-price-asc")
    public ResponseEntity<List<ProductModel>> getProductsOrderByPriceAsc() {
        List<ProductModel> products = productService.getProductsOrderByPriceAsc();
        return ResponseEntity.ok(products);
    }

    /**
     * Get products ordered by price in descending order.
     *
     * @return List of products sorted by price (high to low)
     */
    @GetMapping("/search/sorted-by-price-desc")
    public ResponseEntity<List<ProductModel>> getProductsOrderByPriceDesc() {
        List<ProductModel> products = productService.getProductsOrderByPriceDesc();
        return ResponseEntity.ok(products);
    }

    /**
     * Get products created after a specific date.
     *
     * @param date The date threshold (ISO format: 2024-11-20T10:30:00)
     * @return List of products created after the specified date
     */
    @GetMapping("/search/recent")
    public ResponseEntity<List<ProductModel>> getProductsCreatedAfter(@RequestParam String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        List<ProductModel> products = productService.getProductsCreatedAfter(dateTime);
        return ResponseEntity.ok(products);
    }

    // === Sales Analytics Endpoints (from SaleProductService) ===

    /**
     * Get all products ordered by total sales quantity (bestsellers first).
     *
     * @return List of products ordered by sales performance
     */
    @GetMapping("/analytics/best-sellers")
    public ResponseEntity<List<ProductModel>> getBestSellingProducts() {
        List<ProductModel> products = productService.getBestSellingProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Get top N best-selling products.
     *
     * @param limit Maximum number of products to return
     * @return List of top best-selling products
     */
    @GetMapping("/analytics/top-sellers")
    public ResponseEntity<List<ProductModel>> getTopBestSellingProducts(@RequestParam int limit) {
        List<ProductModel> products = productService.getTopBestSellingProducts(limit);
        return ResponseEntity.ok(products);
    }
}