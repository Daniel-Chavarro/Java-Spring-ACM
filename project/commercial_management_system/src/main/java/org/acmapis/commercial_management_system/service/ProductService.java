package org.acmapis.commercial_management_system.service;

import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.acmapis.commercial_management_system.model.dto.ProductModel;
import org.acmapis.commercial_management_system.repository.ProductRepository;
import org.acmapis.commercial_management_system.utils.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing product-related business logic.
 * Provides CRUD operations and specific queries for product management.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Service
public class ProductService {

    /**
     * Repository interface for accessing product data in the database.
     * Provides CRUD operations and custom queries for product entities.
     */
    private final ProductRepository productRepository;

    /**
     * Mapper interface for converting between ProductEntity and ProductModel objects.
     * Handles automatic mapping using MapStruct framework.
     */
    private final ProductMapper productMapper;

    /**
     * Service for managing sale-product relationships.
     * Used for analytics and relationship management operations.
     */
    private final SaleProductService saleProductService;

    /**
     * Constructs a new ProductService with the required dependencies.
     * Uses constructor-based dependency injection for better testability and immutability.
     *
     * @param productRepository the repository for product data access operations
     * @param productMapper     the mapper for entity-model conversions
     */
    @Autowired
    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          SaleProductService saleProductService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.saleProductService = saleProductService;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return List of ProductModel objects representing all products
     */
    public List<ProductModel> getAllProducts() {
        List<ProductEntity> entities = productRepository.findAll();
        return productMapper.toModelList(entities);
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param productId The unique identifier of the product
     * @return Optional containing the ProductModel if found, empty otherwise
     */
    public Optional<ProductModel> getProductById(UUID productId) {
        Optional<ProductEntity> entity = productRepository.findById(productId);
        return entity.map(productMapper::toModel);
    }

    /**
     * Creates a new product in the database.
     *
     * @param productModel The ProductModel containing the product data to save
     * @return The saved ProductModel with generated ID
     */
    public ProductModel saveProduct(ProductModel productModel) {
        ProductEntity entity = productMapper.toEntity(productModel);
        ProductEntity savedEntity = productRepository.save(entity);
        return productMapper.toModel(savedEntity);
    }

    /**
     * Updates an existing product in the database.
     * Uses the find-modify-save pattern to ensure data integrity and prevent ID conflicts.
     *
     * @param productId    The unique identifier of the product to update
     * @param productModel The ProductModel containing the updated product data
     * @return The updated ProductModel
     * @throws RuntimeException if the product with the given ID is not found
     */
    public ProductModel updateProduct(UUID productId, ProductModel productModel) {
        ProductEntity existingEntity = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        productMapper.updateEntityFromModel(productModel, existingEntity);

        ProductEntity updatedEntity = productRepository.save(existingEntity);
        return productMapper.toModel(updatedEntity);
    }

    /**
     * Deletes a product by its unique identifier.
     *
     * @param productId The unique identifier of the product to delete
     */
    public void deleteProductById(UUID productId) {
        productRepository.deleteById(productId);
    }

    // Custom query methods

    /**
     * Finds products with price within the specified range.
     *
     * @param minPrice The minimum price (inclusive)
     * @param maxPrice The maximum price (inclusive)
     * @return List of ProductModel objects with prices in the specified range
     */
    public List<ProductModel> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        List<ProductEntity> entities = productRepository.findByPriceBetween(minPrice, maxPrice);
        return productMapper.toModelList(entities);
    }

    /**
     * Retrieves all products ordered by price in ascending order.
     *
     * @return List of ProductModel objects sorted by price from lowest to highest
     */
    public List<ProductModel> getProductsOrderByPriceAsc() {
        List<ProductEntity> entities = productRepository.findByOrderByPriceAsc();
        return productMapper.toModelList(entities);
    }

    /**
     * Retrieves all products ordered by price in descending order.
     *
     * @return List of ProductModel objects sorted by price from highest to lowest
     */
    public List<ProductModel> getProductsOrderByPriceDesc() {
        List<ProductEntity> entities = productRepository.findByOrderByPriceDesc();
        return productMapper.toModelList(entities);
    }

    /**
     * Finds products created after the specified date and time.
     *
     * @param createdAfter The datetime threshold for filtering products
     * @return List of ProductModel objects created after the specified datetime
     */
    public List<ProductModel> getProductsCreatedAfter(LocalDateTime createdAfter) {
        List<ProductEntity> entities = productRepository.findByCreatedAtAfter(createdAfter);
        return productMapper.toModelList(entities);
    }

    /**
     * Retrieves all products that belong to a specific category by category name.
     *
     * @param categoryName The unique name of the category
     * @return List of ProductModel objects associated with the specified category
     */
    public List<ProductModel> getProductsByCategoryName(String categoryName) {
        List<ProductEntity> entities = productRepository.findByCategoryName(categoryName);
        return productMapper.toModelList(entities);
    }

    /**
     * Retrieves all products that belong to a specific category by category ID.
     *
     * @param categoryId The unique identifier of the category
     * @return List of ProductModel objects associated with the specified category
     */
    public List<ProductModel> getProductsByCategoryId(Long categoryId) {
        List<ProductEntity> entities = productRepository.findByCategoryId(categoryId);
        return productMapper.toModelList(entities);
    }


    /**
     * Retrieves all products ordered by their total sales quantity in descending order.
     *
     * @return List of ProductModel objects ordered by total sales quantity (highest to lowest)
     */
    public List<ProductModel> getBestSellingProducts() {
        return saleProductService.getBestSellingProducts();
    }

    /**
     * Retrieves the top N best-selling products based on total sales quantity.
     *
     * @param limit The maximum number of top best-selling products to retrieve
     * @return List of ProductModel objects representing the top N best-selling products
     */
    public List<ProductModel> getTopBestSellingProducts(int limit) {
        return saleProductService.getTopBestSellingProducts(limit);
    }

    /**
     * Retrieves all products available in a specific store by store ID.
     *
     * @param storeId The unique identifier of the store
     * @return List of ProductModel objects available in the specified store
     */
    public List<ProductModel> getProductsByStoreId(UUID storeId) {
        List<ProductEntity> entities = productRepository.findByStoreId(storeId);
        return productMapper.toModelList(entities);
    }

    /**
     * Retrieves all products available in a specific store by store name.
     *
     * @param storeName The name of the store
     * @return List of ProductModel objects available in the store with the specified name
     */
    public List<ProductModel> getProductsByStoreName(String storeName) {
        List<ProductEntity> entities = productRepository.findByStoreName(storeName);
        return productMapper.toModelList(entities);
    }
}
