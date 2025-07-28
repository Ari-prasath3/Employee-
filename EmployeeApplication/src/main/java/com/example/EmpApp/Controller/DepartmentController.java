package com.example.EmpApp.Controller;

import com.example.EmpApp.Entity.Department;
import com.example.EmpApp.Service.DepartmentService;
import com.example.EmpApp.dto.DepartmentDto;
import com.example.EmpApp.dto.GenericResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<GenericResponseDto<Department>> createDepartment(@RequestBody DepartmentDto departmentDto) {
        Department department = new Department();
        department.setDepartmentName(departmentDto.getDepartmentName());
        Department created = departmentService.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new GenericResponseDto<>("Department created successfully", created, true)
        );
    }

    @GetMapping
    public ResponseEntity<GenericResponseDto<List<Department>>> getAllDepartment() {
        List<Department> departments = departmentService.getAllDepartment();
        return ResponseEntity.ok(
                new GenericResponseDto<>("Departments retrieved", departments, true)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseDto<Department>> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Department found", department, true)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDto<String>> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Department deleted successfully", null, true)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto<Department>> updateDepartment(
            @PathVariable Long id,
            @RequestBody Department updatedDepartment) {
        Department department = departmentService.updateDepartment(id, updatedDepartment);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Department updated successfully", department, true)
        );
    }

    @DeleteMapping("/reset")
    public ResponseEntity<GenericResponseDto<String>> resetDepartments() {
        departmentService.resetDepartments(); // moved logic to service layer
        return ResponseEntity.ok(
                new GenericResponseDto<>("Departments table reset", null, true)
        );
    }
}
