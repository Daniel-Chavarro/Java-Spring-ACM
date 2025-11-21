package org.acmapis.commercial_management_system.service;

import org.acmapis.commercial_management_system.entity.CategoryEntity;
import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.acmapis.commercial_management_system.model.dto.CategoryModel;
import org.acmapis.commercial_management_system.model.dto.ProductModel;
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
     * Repository for CategoryEntity operations.
     */
    private final CategoryRepository categoryRepository;

    /** Mapper for converting between CategoryEntity and CategoryModel. */
    private final CategoryMapper categoryMapper;

    /** Mapper for converting between ProductEntity and ProductModel. */
    private final ProductMapper productMapper;

    /**
     * Constructor for CategoryService with dependency injection.
     *
     * @param categoryRepository Repository for CategoryEntity operations
     * @param categoryMapper    Mapper for CategoryEntity and CategoryModel
     * @param productMapper     Mapper for ProductEntity and ProductModel
     */
    @Autowired
    public CategoryService(CategoryRepository categoryRepository,
                          CategoryMapper categoryMapper,
                          ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productMapper = productMapper;
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
     *
     * @param categoryModel The CategoryModel containing the updated category data
     * @return The updated CategoryModel
     */
    public CategoryModel updateCategory(CategoryModel categoryModel) {
        CategoryEntity entity = categoryMapper.toEntity(categoryModel);
        CategoryEntity updatedEntity = categoryRepository.save(entity);
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
    public List<CategoryModel> getCategoriesByName(String categoryName) {
        List<CategoryEntity> entities = categoryRepository.findByCategoryName(categoryName);
        return categoryMapper.toModelList(entities);
    }

    /**
     * Retrieves all products that belong to a specific category.
     *
     * @param categoryId The unique identifier of the category
     * @return List of ProductModel objects associated with the specified category
     */
    public List<ProductModel> getProductsByCategoryId(Long categoryId) {
        List<ProductEntity> entities = categoryRepository.findProductsByCategoryId(categoryId);
        return productMapper.toModelList(entities);
    }
}
