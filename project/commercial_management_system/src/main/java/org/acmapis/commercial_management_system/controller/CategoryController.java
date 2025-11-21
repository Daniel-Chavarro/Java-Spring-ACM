package org.acmapis.commercial_management_system.controller;

import org.acmapis.commercial_management_system.model.dto.CategoryModel;
import org.acmapis.commercial_management_system.model.dto.ProductModel;
import org.acmapis.commercial_management_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST Controller for Category management operations.
 * Provides endpoints for CRUD operations and category-related queries.
 *
 * @author Commercial Management System Team
 * @version 1.0
 * @since 2025-11-21
 */
@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Get all categories in the system.
     *
     * @return List of all categories
     */
    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategories() {
        List<CategoryModel> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * Get a specific category by ID.
     *
     * @param id The category ID
     * @return Category if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable Long id) {
        Optional<CategoryModel> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new category.
     *
     * @param category Category data to create
     * @return Created category with 201 status
     */
    @PostMapping
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryModel category) {
        CategoryModel savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    /**
     * Update an existing category.
     *
     * @param id The category ID to update
     * @param category Updated category data
     * @return Updated category
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable Long id, @RequestBody CategoryModel category) {
        category.setCategoryId(id);
        CategoryModel updatedCategory = categoryService.updateCategory(id,category);
        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * Delete a category by ID.
     *
     * @param id The category ID to delete
     * @return 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    // === Category Query Endpoints ===

    /**
     * Find category by exact name match.
     *
     * @param categoryName The exact category name to search for
     * @return Category if found, 404 if not found
     */
    @GetMapping("/search/by-name")
    public ResponseEntity<CategoryModel> findByCategoryName(@RequestParam String categoryName) {
        Optional<CategoryModel> category = categoryService.getCategoriesByName(categoryName);
        return category.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all products belonging to a specific category.
     *
     * @param id The category ID
     * @return List of products in the category
     */
    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductModel>> getProductsByCategoryId(@PathVariable UUID id) {
        List<ProductModel> products = categoryService.findProductsByCategoryId(id);
        return ResponseEntity.ok(products);
    }
}