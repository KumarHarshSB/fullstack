package org.example.fullstack.service;

import org.example.fullstack.model.Departments;
import org.example.fullstack.repository.DepartmentsRepository;
import org.example.fullstack.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentsServiceImpl implements DepartmentsService{
    @Autowired
    DepartmentsRepository departmentsRepository;
    @Autowired
    private EmployeesRepository employeesRepository;

    @Override
    public Departments createDepartment(Departments department){
        employeesRepository.saveAll(department.getEmpList());
        return departmentsRepository.save(department);
    }

    @Override
    public List<Departments> readDepartment(){
        return (List<Departments>) departmentsRepository.findAll();
    }

    @Override
    public Departments updateDepartment(Departments department, Integer depId){
        Departments depVal = departmentsRepository.findById(depId).get();
        depVal.setName(department.getName());
        if(Objects.nonNull(department.getRead_only())){
            depVal.setRead_only(department.getRead_only());
        }
        if(Objects.nonNull(department.getMandatory())) {
            depVal.setMandatory(department.getMandatory());
        }
        if(Objects.nonNull(department.getEmpList())) {
            depVal.setEmpList(department.getEmpList());
        }
        return departmentsRepository.save(depVal);
    }

    @Override
    public void deleteDepartment(Integer depId){
        Departments dep = departmentsRepository.getById(depId);
        if(dep.getRead_only()){
            System.out.println("Read only departments can not be deleted");
            return;
        }
        departmentsRepository.deleteById(depId);
        System.out.println("Departments deleted successfully!");
    }
}
