package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.CityEntity;
import org.acmapis.commercial_management_system.entity.DepartmentEntity;
import org.acmapis.commercial_management_system.entity.UserEntity;
import org.acmapis.commercial_management_system.entity.UserRoleEntity;
import org.acmapis.commercial_management_system.model.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private UserEntity user;
    private CityEntity city;
    private DepartmentEntity department;

    @BeforeEach
    public void setUp() {
        department = new DepartmentEntity();
        department.setDepartmentName("Test Department");
        departmentRepository.save(department);

        city = new CityEntity();
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
    }

    @Test
    public void testFindAll() {
        List<UserEntity> users = userRepository.findAll();
        assertThat(users).isNotNull();
        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        UserEntity foundUser = userRepository.findById(user.getUserId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUserId()).isEqualTo(user.getUserId());
    }

    @Test
    public void testSave() {
        UserEntity newUser = new UserEntity();
        newUser.setFirstName("Jane");
        newUser.setLastName("Doe");
        newUser.setUsername("janedoe");
        newUser.setEmail("jane@example.com");
        newUser.setPassword("password");
        newUser.setPhone("0987654321");
        newUser.setRole(user.getRole());
        newUser.setCity(user.getCity());
        UserEntity savedUser = userRepository.save(newUser);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUserId()).isNotNull();
    }

    @Test
    public void testDeleteById() {
        userRepository.deleteById(user.getUserId());
        UserEntity deletedUser = userRepository.findById(user.getUserId()).orElse(null);
        assertThat(deletedUser).isNull();
    }

    @Test
    public void testFindByLastNameIgnoreCase() {
        List<UserEntity> users = userRepository.findByLastNameIgnoreCase("doe");
        assertThat(users).isNotNull();
        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    public void testFindByCity_CityId() {
        List<UserEntity> users = userRepository.findByCity_CityId(city.getCityId());
        assertThat(users).isNotNull();
        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    public void testFindByCity_CityName() {
        List<UserEntity> users = userRepository.findByCity_CityName("Test City");
        assertThat(users).isNotNull();
        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    public void testFindByCity_Department_DepartmentName() {
        List<UserEntity> users = userRepository.findByCity_Department_DepartmentName("Test Department");
        assertThat(users).isNotNull();
        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    public void testFindByFirstNameLikeIgnoreCase() {
        List<UserEntity> users = userRepository.findByFirstNameLikeIgnoreCase("jo%");
        assertThat(users).isNotNull();
        assertThat(users.size()).isGreaterThan(0);
    }
}
