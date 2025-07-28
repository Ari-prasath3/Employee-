package com.example.EmpApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String employeeName;
    private String phoneNo;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnoreProperties("employee")
    private Department department;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("employee")
    private EmployeeRole employeeRole;

    @ManyToMany
    @JoinTable(
            name = "employee_skill",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @JsonIgnoreProperties("employees")
    private List<Skill> skills;
}
