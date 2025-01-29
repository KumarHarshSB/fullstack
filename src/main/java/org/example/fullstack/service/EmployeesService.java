package org.example.fullstack.service;

import org.example.fullstack.model.Employees;

import java.util.List;

public interface EmployeesService{
    Employees addEmployee(Employees employee);

    List<Employees> readEmployee();

    Employees updateEmployee(Employees employee, Integer id);

    void deleteEmployee(Integer id);
}
