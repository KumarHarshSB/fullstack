package org.example.fullstack.service;

import org.example.fullstack.model.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);

    List<Department> readDepartment();

    Department updateDepartment(Department department);

    void deleteDepartment(Integer depId);

    Department getDepartment(Integer id);
}
