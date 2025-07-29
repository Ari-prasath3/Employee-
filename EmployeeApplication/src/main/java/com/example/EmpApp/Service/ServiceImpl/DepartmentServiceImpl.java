package com.example.EmpApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.EmpApp.Entity.Department;
import com.example.EmpApp.Repository.DepartmentRepository;
import java.util.List;
@Service
public class DepartmentServiceImpl implements  DepartmentService {
    @Autowired
    public DepartmentRepository departmentRepository;
    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
    @Override
    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }
    @Override
    public Department getDepartmentById(Long id){
        return departmentRepository.findById(id)
                .orElseThrow(() ->new RuntimeException("department no found with Id"+id));
    }
    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartment(Long id, Department updatedDepartment) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        existing.setDepartmentName(updatedDepartment.getDepartmentName());
        return departmentRepository.save(existing);
    }

    @Override
    public void resetDepartments() {
        departmentRepository.deleteAll();
    }

}
