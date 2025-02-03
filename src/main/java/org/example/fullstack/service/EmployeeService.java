package org.example.fullstack.service;

import org.example.fullstack.model.Department;
import org.example.fullstack.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> readEmployee();

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

    Employee getEmployee(Integer id);
}
