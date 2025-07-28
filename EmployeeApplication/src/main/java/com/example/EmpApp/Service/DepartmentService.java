package com.example.EmpApp.Service;
import com.example.EmpApp.Entity.Department;

import java.util.List;
public interface DepartmentService {
    Department createDepartment(Department department);
    List<Department> getAllDepartment();
    Department getDepartmentById(Long Id);
    void deleteDepartment(Long id);
    Department updateDepartment(Long id, Department updatedDepartment);
    void resetDepartments();


}
