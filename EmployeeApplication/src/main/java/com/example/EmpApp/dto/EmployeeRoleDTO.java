package com.example.EmpApp.dto;

import lombok.Data;

@Data
public class EmployeeRoleDTO {
    private Long employeeRoleId;
    private String roleName;
    private Long employeeId;
}
