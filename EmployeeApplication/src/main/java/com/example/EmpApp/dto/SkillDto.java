package com.example.EmpApp.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkillDto {
    private Long skillId;
    // In SkillDTO
    @NotBlank
    private String skillName;
    private List<Long> employeeIds;
    }



