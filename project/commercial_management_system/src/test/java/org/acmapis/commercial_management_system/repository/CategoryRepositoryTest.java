package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.CategoryEntity;
import org.acmapis.commercial_management_system.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    private CategoryEntity category;

    @BeforeEach
    public void setUp() {
        category = new CategoryEntity();
        category.setCategoryName("Electronics");
        categoryRepository.save(category);
    }

    @Test
    public void testFindAll() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        assertThat(categories).isNotNull();
        assertThat(categories.size()).isGreaterThan(0);
    }
    
    @Test
    public void testFindById() {
        CategoryEntity foundCategory = categoryRepository.findById(category.getCategoryId()).orElse(null);
        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getCategoryId()).isEqualTo(category.getCategoryId());
    }
    
    @Test
    public void testSave() {
        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setCategoryName("Books");
        CategoryEntity savedCategory = categoryRepository.save(newCategory);
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getCategoryId()).isNotNull();
    }
    
    @Test
    public void testDeleteById() {
        categoryRepository.deleteById(category.getCategoryId());
        CategoryEntity deletedCategory = categoryRepository.findById(category.getCategoryId()).orElse(null);
        assertThat(deletedCategory).isNull();
    }

    @Test
    public void testFindByCategoryName() {
        List<CategoryEntity> categories = categoryRepository.findByCategoryName("Electronics");
        assertThat(categories).isNotNull();
        assertThat(categories.size()).isGreaterThan(0);
        assertThat(categories.getFirst().getCategoryName()).isEqualTo("Electronics");
    }

}