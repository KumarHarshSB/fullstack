package org.example.fullstack.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Department")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Boolean readOnly;
    private Boolean mandatory;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {  CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "departmentList")
    private Set<Employee> employeeList = new HashSet<>();
}
