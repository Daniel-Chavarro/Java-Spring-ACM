package org.acmapis.commercial_management_system.service;

import org.acmapis.commercial_management_system.entity.CategoryEntity;
import org.acmapis.commercial_management_system.model.dto.CategoryModel;
import org.acmapis.commercial_management_system.repository.CategoryRepository;
import org.acmapis.commercial_management_system.utils.mapper.CategoryMapper;
import org.acmapis.commercial_management_system.utils.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing category-related business logic.
 * Provides CRUD operations and specific queries for category management.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Service
public class CategoryService {

    /**
     * Repository interface for accessing category data in the database.
     * Provides CRUD operations and custom queries for category entities.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Mapper interface for converting between CategoryEntity and CategoryModel objects.
     * Handles automatic mapping using MapStruct framework.
     */
    private final CategoryMapper categoryMapper;

    /**
     * Constructs a new CategoryService with the required dependencies.
     * Uses constructor-based dependency injection for better testability and immutability.
     *
     * @param categoryRepository the repository for category data access operations
     * @param categoryMapper     the mapper for category entity-model conversions
     * @param productMapper      the mapper for product entity-model conversions
     */
    @Autowired
    public CategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper,
                           ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Retrieves all categories from the database.
     *
     * @return List of CategoryModel objects representing all categories
     */
    public List<CategoryModel> getAllCategories() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        return categoryMapper.toModelList(entities);
    }

    /**
     * Retrieves a category by its unique identifier.
     *
     * @param categoryId The unique identifier of the category
     * @return Optional containing the CategoryModel if found, empty otherwise
     */
    public Optional<CategoryModel> getCategoryById(Long categoryId) {
        Optional<CategoryEntity> entity = categoryRepository.findById(categoryId);
        return entity.map(categoryMapper::toModel);
    }

    /**
     * Creates a new category in the database.
     *
     * @param categoryModel The CategoryModel containing the category data to save
     * @return The saved CategoryModel with generated ID
     */
    public CategoryModel saveCategory(CategoryModel categoryModel) {
        CategoryEntity entity = categoryMapper.toEntity(categoryModel);
        CategoryEntity savedEntity = categoryRepository.save(entity);
        return categoryMapper.toModel(savedEntity);
    }

    /**
     * Updates an existing category in the database.
     * Uses the find-modify-save pattern to ensure data integrity and prevent ID conflicts.
     *
     * @param categoryId    The unique identifier of the category to update
     * @param categoryModel The CategoryModel containing the updated category data
     * @return The updated CategoryModel
     * @throws RuntimeException if the category with the given ID is not found
     */
    public CategoryModel updateCategory(Long categoryId, CategoryModel categoryModel) {
        CategoryEntity existingEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));

        categoryMapper.updateEntityFromModel(categoryModel, existingEntity);

        CategoryEntity updatedEntity = categoryRepository.save(existingEntity);
        return categoryMapper.toModel(updatedEntity);
    }

    /**
     * Deletes a category by its unique identifier.
     *
     * @param categoryId The unique identifier of the category to delete
     */
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    // Custom query methods

    /**
     * Finds categories by their name.
     *
     * @param categoryName The name of the category to search for
     * @return List of CategoryModel objects matching the specified name
     */
    public Optional<CategoryModel> getCategoriesByName(String categoryName) {
        Optional<CategoryEntity> entity = categoryRepository.findByCategoryName(categoryName);
        return entity.map(categoryMapper::toModel);
    }

}
