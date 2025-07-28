package com.example.EmpApp.Controller;

import com.example.EmpApp.Entity.EmployeeRole;
import com.example.EmpApp.Service.EmployeeRoleService;
import com.example.EmpApp.dto.EmployeeRoleDTO;
import com.example.EmpApp.dto.GenericResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employeerole")
public class EmployeeRoleController {

    private final EmployeeRoleService employeeRoleService;

    @Autowired
    public EmployeeRoleController(EmployeeRoleService employeeRoleService) {
        this.employeeRoleService = employeeRoleService;
    }

    // Create a new EmployeeRole
    @PostMapping
    public ResponseEntity<GenericResponseDto<EmployeeRole>> createRole(@RequestBody EmployeeRoleDTO dto) {
        EmployeeRole created = employeeRoleService.createEmployeerole(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new GenericResponseDto<>("Role created successfully", created, true)
        );
    }

    // Get all roles
    @GetMapping
    public ResponseEntity<GenericResponseDto<List<EmployeeRole>>> getAllRoles() {
        List<EmployeeRole> roles = employeeRoleService.getAllEmployeeRole();
        return ResponseEntity.ok(
                new GenericResponseDto<>("All roles retrieved", roles, true)
        );
    }

    // Get role by ID
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseDto<EmployeeRole>> getRoleById(@PathVariable Long id) {
        EmployeeRole role = employeeRoleService.getEmployeeroleById(id);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Role retrieved", role, true)
        );
    }

    // Update role by ID
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto<EmployeeRole>> updateRole(@PathVariable Long id,
                                                                       @RequestBody EmployeeRoleDTO dto) {
        EmployeeRole updated = employeeRoleService.updateEmployeerole(id, dto);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Role updated successfully", updated, true)
        );
    }

    // Delete role by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDto<String>> deleteRole(@PathVariable Long id) {
        employeeRoleService.deleteEmployeerole(id);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Deleted EmployeeRole with ID: " + id, null, true)
        );
    }

    // Delete all roles
    @DeleteMapping("/all")
    public ResponseEntity<GenericResponseDto<List<EmployeeRole>>> deleteAllRoles() {
        List<EmployeeRole> deleted = employeeRoleService.deleteAllEmployeeRole();
        return ResponseEntity.ok(
                new GenericResponseDto<>("All roles deleted", deleted, true)
        );
    }
}
