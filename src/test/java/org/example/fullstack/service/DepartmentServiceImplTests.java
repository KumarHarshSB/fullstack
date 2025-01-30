package org.example.fullstack.service;

import org.example.fullstack.model.Department;
import org.example.fullstack.model.Employee;
import org.example.fullstack.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setId(1);
        department.setName("HR");
        department.setReadOnly(false);
        department.setMandatory(true);
    }

    @Test
    void testCreateDepartment() {
        when(departmentRepository.save(department)).thenReturn(department);
        Department created = departmentService.createDepartment(department);
        assertNotNull(created);
        assertEquals("HR", created.getName());
    }

    @Test
    void testReadDepartment() {
        when(departmentRepository.findAll()).thenReturn(Collections.singletonList(department));
        List<Department> departments = departmentService.readDepartment();
        assertFalse(departments.isEmpty());
        assertEquals(1, departments.size());
    }

    @Test
    void testUpdateDepartment() {
        Department updatedDepartment = new Department();
        updatedDepartment.setId(1);
        updatedDepartment.setName("Finance");
        updatedDepartment.setReadOnly(true);
        updatedDepartment.setMandatory(false);
        updatedDepartment.setEmployeeList(Collections.singleton(new Employee()));

        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(updatedDepartment);

        Department result = departmentService.updateDepartment(updatedDepartment);
        assertEquals("Finance", result.getName());
        assertTrue(result.getReadOnly());
    }

    @Test
    void testDeleteDepartmentNonReadOnly() {
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));

        departmentService.deleteDepartment(1);
        verify(departmentRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteDepartmentReadOnly() {
        department.setReadOnly(true);
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));

        departmentService.deleteDepartment(1);
        verify(departmentRepository, never()).deleteById(anyInt());
    }
}
