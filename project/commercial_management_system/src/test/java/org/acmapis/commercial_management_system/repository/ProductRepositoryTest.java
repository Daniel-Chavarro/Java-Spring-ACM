package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.CategoryEntity;
import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ProductEntity product;

    @BeforeEach
    public void setUp() {
        CategoryEntity category = new CategoryEntity();
        category.setCategoryName("Electronics");
        categoryRepository.save(category);

        product = new ProductEntity();
        product.setProductName("Laptop");
        product.setProductDescription("A powerful laptop");
        product.setPrice(1200.00);
        productRepository.save(product);
    }

    @Test
    public void testFindAll() {
        List<ProductEntity> products = productRepository.findAll();
        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        ProductEntity foundProduct = productRepository.findById(product.getProductId()).orElse(null);
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getProductId()).isEqualTo(product.getProductId());
    }

    @Test
    public void testSave() {
        ProductEntity newProduct = new ProductEntity();
        newProduct.setProductName("Keyboard");
        newProduct.setProductDescription("A mechanical keyboard");
        newProduct.setPrice(150.00);
        ProductEntity savedProduct = productRepository.save(newProduct);
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getProductId()).isNotNull();
    }

    @Test
    public void testDeleteById() {
        productRepository.deleteById(product.getProductId());
        ProductEntity deletedProduct = productRepository.findById(product.getProductId()).orElse(null);
        assertThat(deletedProduct).isNull();
    }

    @Test
    public void testFindByPriceBetween() {
        List<ProductEntity> products = productRepository.findByPriceBetween(1000.00, 1500.00);
        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(0);
        assertThat(products.getFirst().getPrice()).isBetween(1000.00, 1500.00);
    }

    @Test
    public void testFindByOrderByPriceAsc() {
        List<ProductEntity> products = productRepository.findByOrderByPriceAsc();
        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(1);
        assertThat(products.get(0).getPrice()).isLessThanOrEqualTo(products.get(1).getPrice());
    }

    @Test
    public void testFindByOrderByPriceDesc() {
        List<ProductEntity> products = productRepository.findByOrderByPriceDesc();
        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(1);
        assertThat(products.get(0).getPrice()).isGreaterThanOrEqualTo(products.get(1).getPrice());
    }

    @Test
    public void testFindByCreatedAtAfter() {
        List<ProductEntity> products = productRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1));
        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(0);
    }
}
