package org.example.fullstack.controller;

import org.example.fullstack.model.Employees;
import org.example.fullstack.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeesService employeesService;

    @PostMapping
    public Employees createEmployee(@RequestBody Employees employee){
        return employeesService.addEmployee(employee);
    }

    @GetMapping
    public List<Employees> readEmployee(){
        return employeesService.readEmployee();
    }

    @PutMapping("/{id}")
    public Employees updateEmployee(@RequestBody Employees employee, @PathVariable Integer id){
        return employeesService.updateEmployee(employee,id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id){
        employeesService.deleteEmployee(id);
    }
}
