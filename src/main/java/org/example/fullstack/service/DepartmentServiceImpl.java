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
        if(department.getName() == null  || department.getName().trim().isEmpty())
        {
            log.warn("Department name can not be null or empty.");
            throw new IllegalArgumentException("Department name is required.");
        }
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> readDepartment(){
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Department department){
        if(department.getName() == null  || department.getName().trim().isEmpty())
        {
            log.warn("Department name can not be made null or empty.");
            throw new IllegalArgumentException("Department name is required.");
        }

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
            throw new UnsupportedOperationException("Deletion not allowed: Department is read-only.");
        }
        log.info("Department deleted successfully.");
        departmentRepository.deleteById(depId);
    }

    @Override
    public Department getDepartment(Integer id) {
        return departmentRepository.getDepartmentById(id);
    }
}
