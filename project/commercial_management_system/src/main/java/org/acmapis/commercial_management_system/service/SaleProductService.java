package org.acmapis.commercial_management_system.service;

import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.acmapis.commercial_management_system.entity.SaleProductEntity;
import org.acmapis.commercial_management_system.model.dto.ProductModel;
import org.acmapis.commercial_management_system.model.dto.SaleProductModel;
import org.acmapis.commercial_management_system.repository.SaleProductRepository;
import org.acmapis.commercial_management_system.utils.mapper.ProductMapper;
import org.acmapis.commercial_management_system.utils.mapper.SaleProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing sale-product relationship business logic.
 * Provides CRUD operations and analytics queries for sale-product relationships.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Service
public class SaleProductService {

    /**
     * Repository interface for accessing sale-product relationship data in the database.
     * Provides CRUD operations and analytics queries for sale-product entities.
     */
    private final SaleProductRepository saleProductRepository;

    /**
     * Mapper interface for converting between SaleProductEntity and SaleProductModel objects.
     * Handles automatic mapping using MapStruct framework.
     */
    private final SaleProductMapper saleProductMapper;

    /**
     * Mapper interface for converting between ProductEntity and ProductModel objects.
     * Used for mapping product data in analytics operations.
     */
    private final ProductMapper productMapper;

    /**
     * Constructs a new SaleProductService with the required dependencies.
     * Uses constructor-based dependency injection for better testability and immutability.
     *
     * @param saleProductRepository the repository for sale-product relationship data access
     * @param saleProductMapper     the mapper for sale-product entity-model conversions
     * @param productMapper         the mapper for product entity-model conversions
     */
    @Autowired
    public SaleProductService(SaleProductRepository saleProductRepository,
                              SaleProductMapper saleProductMapper,
                              ProductMapper productMapper) {
        this.saleProductRepository = saleProductRepository;
        this.saleProductMapper = saleProductMapper;
        this.productMapper = productMapper;
    }

    /**
     * Retrieves all sale-product relationships from the database.
     *
     * @return List of SaleProductModel objects representing all sale-product relationships
     */
    public List<SaleProductModel> getAllSaleProducts() {
        List<SaleProductEntity> entities = saleProductRepository.findAll();
        return saleProductMapper.toModelList(entities);
    }

    /**
     * Retrieves a sale-product relationship by its unique identifier.
     *
     * @param saleProductId The unique identifier of the sale-product relationship
     * @return Optional containing the SaleProductModel if found, empty otherwise
     */
    public Optional<SaleProductModel> getSaleProductById(Long saleProductId) {
        Optional<SaleProductEntity> entity = saleProductRepository.findById(saleProductId);
        return entity.map(saleProductMapper::toModel);
    }

    /**
     * Creates a new sale-product relationship in the database.
     *
     * @param saleProductModel The SaleProductModel containing the relationship data to save
     * @return The saved SaleProductModel with generated ID
     */
    public SaleProductModel saveSaleProduct(SaleProductModel saleProductModel) {
        SaleProductEntity entity = saleProductMapper.toEntity(saleProductModel);
        SaleProductEntity savedEntity = saleProductRepository.save(entity);
        return saleProductMapper.toModel(savedEntity);
    }

    /**
     * Updates an existing sale-product relationship in the database.
     * Uses the find-modify-save pattern to ensure data integrity and prevent ID conflicts.
     *
     * @param saleProductId    The unique identifier of the sale-product relationship to update
     * @param saleProductModel The SaleProductModel containing the updated relationship data
     * @return The updated SaleProductModel
     * @throws RuntimeException if the sale-product relationship with the given ID is not found
     */
    public SaleProductModel updateSaleProduct(Long saleProductId, SaleProductModel saleProductModel) {
        SaleProductEntity existingEntity = saleProductRepository.findById(saleProductId)
                .orElseThrow(() -> new RuntimeException("SaleProduct not found with ID: " + saleProductId));

        saleProductMapper.updateEntityFromModel(saleProductModel, existingEntity);

        SaleProductEntity updatedEntity = saleProductRepository.save(existingEntity);
        return saleProductMapper.toModel(updatedEntity);
    }

    /**
     * Deletes a sale-product relationship by its unique identifier.
     *
     * @param saleProductId The unique identifier of the sale-product relationship to delete
     */
    public void deleteSaleProductById(Long saleProductId) {
        saleProductRepository.deleteById(saleProductId);
    }

    // Custom query methods

    /**
     * Retrieves all products ordered by their total sales quantity in descending order.
     * This method aggregates the quantity sold for each product across all sales.
     *
     * @return List of ProductModel objects ordered by total sales quantity (highest to lowest)
     */
    public List<ProductModel> getBestSellingProducts() {
        List<ProductEntity> entities = saleProductRepository.findBestSellingProducts();
        return productMapper.toModelList(entities);
    }

    /**
     * Retrieves the top N best-selling products ordered by total sales quantity.
     *
     * @param limit The maximum number of products to return
     * @return List of ProductModel objects representing the top best-selling products
     */
    public List<ProductModel> getTopBestSellingProducts(int limit) {
        List<ProductEntity> entities = saleProductRepository.findTopBestSellingProducts(limit);
        return productMapper.toModelList(entities);
    }
}
