package org.example.fullstack.service;

import org.example.fullstack.model.Department;
import org.example.fullstack.model.Employee;
import org.example.fullstack.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> readEmployee(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee){
        Employee old  = employeeRepository.findById(employee.getId()).get();
        old.setName(employee.getName());
        old.setEmail(employee.getEmail());
        for(Department d: employee.getDepartmentList())
            old.getDepartmentList().add(d);
        return employeeRepository.save(old);
    }

    @Override
    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
    }
}
