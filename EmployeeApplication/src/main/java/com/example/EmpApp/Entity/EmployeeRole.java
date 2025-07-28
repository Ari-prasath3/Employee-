package com.example.EmpApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeRoleId;

    @Column(nullable = false, unique = true)
    private String roleName;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("employeeRole")
    private Employee employee;
}
