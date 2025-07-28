package com.example.EmpApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(nullable = false, unique = true)
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("department")
    private List<Employee> employee;
}
