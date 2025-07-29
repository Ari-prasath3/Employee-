package com.example.EmpApp.Service;

import com.example.EmpApp.Entity.Employee;
import com.example.EmpApp.Entity.EmployeeRole;
import com.example.EmpApp.Repository.EmployeeRepository;
import com.example.EmpApp.Repository.EmployeeroleRepository;
import com.example.EmpApp.dto.EmployeeRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

    @Autowired
    private EmployeeroleRepository employeeroleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeRole createEmployeerole(EmployeeRoleDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmployeeRole role = new EmployeeRole();
        role.setRoleName(dto.getRoleName());
        role.setEmployee(employee);
        employee.setEmployeeRole(role);
        employeeRepository.save(employee);


        return role;

//        return employeeroleRepository.save(role);
    }

    @Override
    public EmployeeRole getEmployeeroleById(Long id) {
        return employeeroleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public List<EmployeeRole> getAllEmployeeRole() {
        return employeeroleRepository.findAll();
    }

    @Override
    public EmployeeRole updateEmployeerole(Long id, EmployeeRoleDTO dto) {
        EmployeeRole role = employeeroleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        role.setRoleName(dto.getRoleName());

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        role.setEmployee(employee);
        return employeeroleRepository.save(role);
    }

    @Override
    public void deleteEmployeerole(Long id) {
        employeeroleRepository.deleteById(id);
    }

    @Override
    public List<EmployeeRole> deleteAllEmployeeRole() {
        List<EmployeeRole> all = employeeroleRepository.findAll();
        employeeroleRepository.deleteAll();
        return all;
    }
}
