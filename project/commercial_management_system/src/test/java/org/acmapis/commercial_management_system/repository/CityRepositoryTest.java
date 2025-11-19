package org.acmapis.commercial_management_system.repository;

import org.acmapis.commercial_management_system.entity.CityEntity;
import org.acmapis.commercial_management_system.entity.DepartmentEntity;
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
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

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
    }

    @Test
    public void testFindAll() {
        List<CityEntity> cities = cityRepository.findAll();
        assertThat(cities).isNotNull();
        assertThat(cities.size()).isGreaterThan(0);
    }
    
    @Test
    public void testFindById() {
        CityEntity foundCity = cityRepository.findById(city.getCityId()).orElse(null);
        assertThat(foundCity).isNotNull();
        assertThat(foundCity.getCityId()).isEqualTo(city.getCityId());
    }
    
    @Test
    public void testSave() {
        CityEntity newCity = new CityEntity();
        newCity.setCityName("New City");
        newCity.setDepartment(department);
        CityEntity savedCity = cityRepository.save(newCity);
        assertThat(savedCity).isNotNull();
        assertThat(savedCity.getCityId()).isNotNull();
    }
    
    @Test
    public void testDeleteById() {
        cityRepository.deleteById(city.getCityId());
        CityEntity deletedCity = cityRepository.findById(city.getCityId()).orElse(null);
        assertThat(deletedCity).isNull();
    }
}