package org.example.fullstack.service;

import org.example.fullstack.model.Departments;

import java.util.List;

public interface DepartmentsService{
    Departments createDepartment(Departments department);

    List<Departments> readDepartment();

    Departments updateDepartment(Departments department, Integer depId);

    void deleteDepartment(Integer depId);
}
