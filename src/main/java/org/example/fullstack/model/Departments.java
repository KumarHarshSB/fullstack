package org.example.fullstack.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Departments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Boolean read_only;
    private Boolean mandatory;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {  CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "depList")
    private Set<Employees> empList = new HashSet<>();
}
