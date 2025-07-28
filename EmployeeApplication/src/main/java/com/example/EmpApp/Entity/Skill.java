package com.example.EmpApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @Column(nullable = false)
    private String skillName;

    @ManyToMany(mappedBy = "skills")
    @JsonIgnoreProperties("skills")
    private List<Employee> employees;
}
