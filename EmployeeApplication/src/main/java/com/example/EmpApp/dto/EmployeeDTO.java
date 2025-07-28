package com.example.EmpApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {

    private Long employeeId;

    @NotBlank(message = "Employee name is required")
    private String employeeName;

    @Email(message = "Please enter a valid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @NotBlank(message = "Phone number is required")
    private String phoneNo;

    @Size(min = 6, message = "Password must be at least 6 characters")
    @NotBlank(message = "Password is required")
    private String password;
    //new
    @NotNull(message="Department id required")
    private Long departmentId;

    private String departmentName;

    private Long  employeeRoleId;
    private String roleName;
    private List<Long> skillIds;
    private List<String> skillNames;
    private List<SkillDto> skills;


}
