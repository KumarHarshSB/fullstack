package org.example.fullstack.service;

import org.example.fullstack.model.Department;
import org.example.fullstack.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTests {

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
        department.setMandatory(false);
        department.setEmployeeList(new HashSet<>());
    }

    @Test
    public void testCreateDepartment_ValidDepartment_Success() {
        when(departmentRepository.save(department)).thenReturn(department);

        Department savedDepartment = departmentService.createDepartment(department);

        assertNotNull(savedDepartment);
        assertEquals("HR", savedDepartment.getName());
    }

    @Test
    public void testCreateDepartment_NullName_ThrowsIllegalArgumentException() {
        department.setName(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                departmentService.createDepartment(department));

        assertEquals("Department name is required.", exception.getMessage());
    }

    @Test
    public void testReadDepartment_NoConditions_ReturnsList() {
        List<Department> departments = List.of(department);
        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> result = departmentService.readDepartment();

        assertEquals(1, result.size());
    }

    @Test
    public void testUpdateDepartment_ValidDepartment_Success() {
        Department updated = new Department();
        updated.setId(1);
        updated.setName("Finance");
        updated.setReadOnly(false);
        updated.setMandatory(false);
        updated.setEmployeeList(new HashSet<>());

        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(departmentRepository.save(any())).thenReturn(updated);

        Department result = departmentService.updateDepartment(updated);

        assertEquals("Finance", result.getName());
    }

    @Test
    public void testUpdateDepartment_NullName_ThrowsIllegalArgumentException() {
        department.setName(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                departmentService.updateDepartment(department));

        assertEquals("Department name is required.", exception.getMessage());
    }

    @Test
    public void testDeleteDepartment_ReadOnly_ThrowsUnsupportedOperationException() {
        department.setReadOnly(true);

        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));

        Exception exception = assertThrows(UnsupportedOperationException.class, () ->
                departmentService.deleteDepartment(1));

        assertEquals("Deletion not allowed: Department is read-only.", exception.getMessage());
    }

    @Test
    public void testDeleteDepartment_ValidDepartment_Success() {
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        doNothing().when(departmentRepository).deleteById(1);

        assertDoesNotThrow(() -> departmentService.deleteDepartment(1));
    }
}
