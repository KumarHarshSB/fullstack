package org.example.fullstack.controller;

import org.example.fullstack.model.Department;
import org.example.fullstack.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department){
        return departmentService.createDepartment(department);
    }

    @GetMapping
    public List<Department> readDepartment(){
        return departmentService.readDepartment();
    }

    @PutMapping()
    public Department updateDepartment(@RequestBody Department department){
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Integer id){
        departmentService.deleteDepartment(id);
    }
}
