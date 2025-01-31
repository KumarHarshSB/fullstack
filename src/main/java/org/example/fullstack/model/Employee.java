package org.example.fullstack.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employee")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "MAP_EMPLOYEE_DEPARTMENT",
            joinColumns = @JoinColumn(name = "employeeId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "departmentId", referencedColumnName = "id"))
    private Set<Department> departmentList = new HashSet<>();
}
