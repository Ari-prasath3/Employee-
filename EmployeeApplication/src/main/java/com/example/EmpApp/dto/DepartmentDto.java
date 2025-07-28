package com.example.EmpApp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.persistence.*;
@Data

public class DepartmentDto {

    private Long departmentId;
    @NotBlank(message="department name required")
    private String departmentName;
}
