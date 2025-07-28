package com.example.EmpApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.EmpApp.Entity.Department;
public interface DepartmentRepository extends JpaRepository<Department,Long> {


}
