package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreProductRepository storeProductRepository;

    private StoreEntity store;
    private CityEntity city;
    private ProductEntity product;

    @BeforeEach
    public void setUp() {
        DepartmentEntity department = new DepartmentEntity();
        department.setDepartmentName("Test Department");
        departmentRepository.save(department);

        city = new CityEntity();
        city.setCityName("Test City");
        city.setDepartment(department);
        cityRepository.save(city);

        store = new StoreEntity();
        store.setStoreName("Test Store");
        store.setCity(city);
        storeRepository.save(store);

        product = new ProductEntity();
        product.setProductName("Laptop");
        product.setProductDescription("A powerful laptop");
        product.setPrice(1200.00);
        productRepository.save(product);

        StoreProductEntity storeProduct = new StoreProductEntity();
        storeProduct.setStore(store);
        storeProduct.setProduct(product);
        storeProduct.setStock(10L);
        storeProduct.setAddress("123 Main St");
        storeProductRepository.save(storeProduct);
    }

    @Test
    public void testFindAll() {
        List<StoreEntity> stores = storeRepository.findAll();
        assertThat(stores).isNotNull();
        assertThat(stores.size()).isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        StoreEntity foundStore = storeRepository.findById(store.getStoreId()).orElse(null);
        assertThat(foundStore).isNotNull();
        assertThat(foundStore.getStoreId()).isEqualTo(store.getStoreId());
    }

    @Test
    public void testSave() {
        StoreEntity newStore = new StoreEntity();
        newStore.setStoreName("New Store");
        newStore.setCity(city);
        StoreEntity savedStore = storeRepository.save(newStore);
        assertThat(savedStore).isNotNull();
        assertThat(savedStore.getStoreId()).isNotNull();
    }

    @Test
    public void testDeleteById() {
        storeRepository.deleteById(store.getStoreId());
        StoreEntity deletedStore = storeRepository.findById(store.getStoreId()).orElse(null);
        assertThat(deletedStore).isNull();
    }

    @Test
    public void testFindByCity_CityId() {
        List<StoreEntity> stores = storeRepository.findByCity_CityId(city.getCityId());
        assertThat(stores).isNotNull();
        assertThat(stores.size()).isGreaterThan(0);
    }

    @Test
    public void testFindByCity_CityName() {
        List<StoreEntity> stores = storeRepository.findByCity_CityName("Test City");
        assertThat(stores).isNotNull();
        assertThat(stores.size()).isGreaterThan(0);
    }

    @Test
    public void testFindProductsByStoreId() {
        List<ProductEntity> products = storeRepository.findProductsByStoreId(store.getStoreId());
        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    public void testFindProductByStoreName() {
        List<ProductEntity> products = storeRepository.findProductByStoreName("Test Store");
        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    public void testFindStoreProductsByStoreId() {
        List<StoreProductEntity> storeProducts = storeRepository.findStoreProductsByStoreId(store.getStoreId());
        assertThat(storeProducts).isNotNull();
        assertThat(storeProducts.size()).isGreaterThan(0);
    }
}
