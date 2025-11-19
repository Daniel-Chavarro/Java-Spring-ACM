package org.acmapis.commercial_management_system.repository;

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
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    private DepartmentEntity department;

    @BeforeEach
    public void setUp() {
        department = new DepartmentEntity();
        department.setDepartmentName("Test Department");
        departmentRepository.save(department);
    }

    @Test
    public void testFindAll() {
        List<DepartmentEntity> departments = departmentRepository.findAll();
        assertThat(departments).isNotNull();
        assertThat(departments.size()).isGreaterThan(0);
    }
    
    @Test
    public void testFindById() {
        DepartmentEntity foundDepartment = departmentRepository.findById(department.getDepartmentId()).orElse(null);
        assertThat(foundDepartment).isNotNull();
        assertThat(foundDepartment.getDepartmentId()).isEqualTo(department.getDepartmentId());
    }
    
    @Test
    public void testSave() {
        DepartmentEntity newDepartment = new DepartmentEntity();
        newDepartment.setDepartmentName("New Department");
        DepartmentEntity savedDepartment = departmentRepository.save(newDepartment);
        assertThat(savedDepartment).isNotNull();
        assertThat(savedDepartment.getDepartmentId()).isNotNull();
    }
    
    @Test
    public void testDeleteById() {
        departmentRepository.deleteById(department.getDepartmentId());
        DepartmentEntity deletedDepartment = departmentRepository.findById(department.getDepartmentId()).orElse(null);
        assertThat(deletedDepartment).isNull();
    }
}