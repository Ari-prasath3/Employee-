package com.example.EmpApp.Controller;

import com.example.EmpApp.Service.EmployeeService;
import com.example.EmpApp.dto.EmployeeDTO;
import com.example.EmpApp.dto.GenericResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<GenericResponseDto<List<EmployeeDTO>>> getAll() {
        List<EmployeeDTO> employees = service.getAll();
        return ResponseEntity.ok(
                new GenericResponseDto<>("All employees retrieved", employees, true)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseDto<EmployeeDTO>> getById(@PathVariable Long id) {
        EmployeeDTO employee = service.getById(id);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Employee found", employee, true)
        );
    }

    @PostMapping
    public ResponseEntity<GenericResponseDto<EmployeeDTO>> create(@Valid @RequestBody EmployeeDTO dto) {
        EmployeeDTO saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new GenericResponseDto<>("Employee created successfully", saved, true)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto<EmployeeDTO>> updateEmployee(@PathVariable Long id,
                                                                          @Valid @RequestBody EmployeeDTO dto) {
        EmployeeDTO updated = service.update(id, dto);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Employee updated successfully", updated, true)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDto<String>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Employee deleted successfully", null, true)
        );
    }

    @DeleteMapping("/reset")
    public ResponseEntity<GenericResponseDto<String>> resetEmployee() {
        service.resetEmployees();  // You must move deleteAll() logic to service layer
        return ResponseEntity.ok(
                new GenericResponseDto<>("Employee table reset", null, true)
        );
    }
}
