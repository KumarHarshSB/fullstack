package org.example.fullstack.service;

import lombok.extern.slf4j.Slf4j;
import org.example.fullstack.model.Department;
import org.example.fullstack.model.Employee;
import org.example.fullstack.repository.DepartmentRepository;
import org.example.fullstack.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee){
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            log.warn("Employee name cannot be null or empty.");
            throw new IllegalArgumentException("Employee name is required.");
        }

        Set<Department> attachedDepartments = new HashSet<>();
        for (Department dept : employee.getDepartmentList()) {
            Department existingDept = (Department) departmentRepository.findByName(dept.getName())
                    .orElseThrow(() -> new RuntimeException("Department not found: " + dept.getName()));
            attachedDepartments.add(existingDept);
        }

        employee.setDepartmentList(attachedDepartments);

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> readEmployee(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee){
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            log.warn("Employee name cannot be made null or empty.");
            throw new IllegalArgumentException("Employee name is required.");
        }

        Employee old  = employeeRepository.findById(employee.getId()).orElseThrow(
                () -> new RuntimeException("Employee not found"));

        old.setName(employee.getName());
        old.setEmail(employee.getEmail());

        Set<Department> updatedDepartments = new HashSet<>();
        for (Department dept : employee.getDepartmentList()) {
            Department existingDept = (Department) departmentRepository.findByName(dept.getName())
                    .orElseThrow(() -> new RuntimeException("Department not found: " + dept.getName()));
            updatedDepartments.add(existingDept);
        }
        return employeeRepository.save(old);
    }

    @Override
    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee getEmployee(Integer id) {
        return employeeRepository.getEmployeeById(id);
    }
}
