package org.example.fullstack.repository;

import org.example.fullstack.model.Departments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DepartmentsRepository extends CrudRepository<Departments, Integer> {
    Departments getById(Integer id);

    @Query("select d from Departments d where d.mandatory is true")
    Set<Departments> findAllByMandatoryEquals(Boolean value);
}
