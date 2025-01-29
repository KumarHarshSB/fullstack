package org.example.fullstack.controller;

import org.example.fullstack.model.Departments;
import org.example.fullstack.repository.EmployeesRepository;
import org.example.fullstack.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    @Autowired
    private DepartmentsService departmentsService;
    @Autowired
    private EmployeesRepository employeesRepository;

    @PostMapping
    public Departments createDepartment(@RequestBody Departments department){
        if(department.getMandatory()) {
            employeesRepository.findAll().forEach(e -> e.getDepList().add(department));
        }
        return departmentsService.createDepartment(department);
    }

    @GetMapping
    public List<Departments> readDepartment(){
        return departmentsService.readDepartment();
    }

    @PutMapping("/{id}")
    public Departments updateDepartment(@RequestBody Departments departments, @PathVariable Integer id){
        return departmentsService.updateDepartment(departments,id);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Integer id){
        departmentsService.deleteDepartment(id);
    }
}
