package org.example.fullstack.service;

import lombok.extern.slf4j.Slf4j;
import org.example.fullstack.model.Department;
import org.example.fullstack.model.Employee;
import org.example.fullstack.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> readDepartment(){
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Department department){
        Department old  = departmentRepository.findById(department.getId()).get();
        old.setName(department.getName());
        old.setReadOnly(department.getReadOnly());
        old.setMandatory(department.getMandatory());
        for(Employee e: department.getEmployeeList())
            old.getEmployeeList().add(e);
        return departmentRepository.save(old);
    }

    @Override
    public void deleteDepartment(Integer depId){
        Department dep = departmentRepository.findById(depId).get();
        if(dep.getReadOnly()){
            log.warn("Read Only department can not be deleted");
            return;
        }
        log.info("Department deleted successfully.");
        departmentRepository.deleteById(depId);
    }
}
