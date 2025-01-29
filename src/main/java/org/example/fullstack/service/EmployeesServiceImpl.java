package org.example.fullstack.service;

import org.example.fullstack.model.Departments;
import org.example.fullstack.model.Employees;
import org.example.fullstack.repository.DepartmentsRepository;
import org.example.fullstack.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmployeesServiceImpl implements EmployeesService{
    @Autowired
    public EmployeesRepository employeesRepository;
    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Override
    public Employees addEmployee(Employees employee){
        Set<Departments> mandatoryDeps = departmentsRepository.findAllByMandatoryEquals(true);
        employee.setDepList(mandatoryDeps);
        departmentsRepository.saveAll(mandatoryDeps);
        return employeesRepository.save(employee);
    }

    @Override
    public List<Employees> readEmployee(){
        return (List<Employees>) employeesRepository.findAll();
    }

    @Override
    public Employees updateEmployee(Employees employee, Integer id){
        Employees empVal = employeesRepository.findById(id).get();
        empVal.setName(employee.getName());
        empVal.setEmail(employee.getEmail());
        empVal.setDepList(employee.getDepList());
        return employeesRepository.save(empVal);
    }

    @Override
    public void deleteEmployee(Integer id){
        employeesRepository.deleteById(id);
    }
}
