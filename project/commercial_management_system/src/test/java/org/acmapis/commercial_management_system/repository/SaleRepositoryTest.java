package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.CityEntity;
import org.acmapis.commercial_management_system.entity.DepartmentEntity;
import org.acmapis.commercial_management_system.entity.SaleEntity;
import org.acmapis.commercial_management_system.entity.UserEntity;
import org.acmapis.commercial_management_system.entity.UserRoleEntity;
import org.acmapis.commercial_management_system.model.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SaleRepositoryTest {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private SaleEntity sale;
    private UserEntity user;

    @BeforeEach
    public void setUp() {
        DepartmentEntity department = new DepartmentEntity();
        department.setDepartmentName("Test Department");
        departmentRepository.save(department);

        CityEntity city = new CityEntity();
        city.setCityName("Test City");
        city.setDepartment(department);
        cityRepository.save(city);

        UserRoleEntity role = new UserRoleEntity();
        role.setRole(UserRole.USER);
        userRoleRepository.save(role);

        user = new UserEntity();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setEmail("johndoe@example.com");
        user.setPassword("password");
        user.setPhone("1234567890");
        user.setRole(role);
        user.setCity(city);
        userRepository.save(user);

        sale = new SaleEntity();
        sale.setTotalAmount(100L);
        sale.setUser(user);
        saleRepository.save(sale);
    }

    @Test
    public void testFindAll() {
        List<SaleEntity> sales = saleRepository.findAll();
        assertThat(sales).isNotNull();
        assertThat(sales.size()).isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        SaleEntity foundSale = saleRepository.findById(sale.getSaleId()).orElse(null);
        assertThat(foundSale).isNotNull();
        assertThat(foundSale.getSaleId()).isEqualTo(sale.getSaleId());
    }

    @Test
    public void testSave() {
        SaleEntity newSale = new SaleEntity();
        newSale.setTotalAmount(200L);
        newSale.setUser(user);
        SaleEntity savedSale = saleRepository.save(newSale);
        assertThat(savedSale).isNotNull();
        assertThat(savedSale.getSaleId()).isNotNull();
    }

    @Test
    public void testDeleteById() {
        saleRepository.deleteById(sale.getSaleId());
        SaleEntity deletedSale = saleRepository.findById(sale.getSaleId()).orElse(null);
        assertThat(deletedSale).isNull();
    }

    @Test
    public void testFindByUser_UserId() {
        List<SaleEntity> sales = saleRepository.findByUser_UserId(user.getUserId());
        assertThat(sales).isNotNull();
        assertThat(sales.size()).isGreaterThan(0);
    }

    @Test
    public void testFindByUser_() {
        List<SaleEntity> sales = saleRepository.findByUser_(user);
        assertThat(sales).isNotNull();
        assertThat(sales.size()).isGreaterThan(0);
    }

    @Test
    public void testFindByUser_FirstName() {
        List<SaleEntity> sales = saleRepository.findByUser_FirstName("John");
        assertThat(sales).isNotNull();
        assertThat(sales.size()).isGreaterThan(0);
    }

    @Test
    public void testSumTotalAmountBySaleDate() {
        Long totalAmount = saleRepository.sumTotalAmountBySaleDate(sale.getSaleDate());
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isGreaterThan(0);
    }

    @Test
    public void testFindByTotalAmountAfter() {
        List<SaleEntity> sales = saleRepository.findByTotalAmountAfter(50L);
        assertThat(sales).isNotNull();
        assertThat(sales.size()).isGreaterThan(0);
    }
}
