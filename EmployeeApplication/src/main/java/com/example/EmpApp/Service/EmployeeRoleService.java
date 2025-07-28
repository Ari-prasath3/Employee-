package com.example.EmpApp.Service;

import com.example.EmpApp.Entity.EmployeeRole;
import com.example.EmpApp.dto.EmployeeRoleDTO;


import java.util.List;

public interface EmployeeRoleService {

    EmployeeRole createEmployeerole(EmployeeRoleDTO employeeroleDTO);
    EmployeeRole getEmployeeroleById(Long id);
    List<EmployeeRole> getAllEmployeeRole();
    EmployeeRole updateEmployeerole(Long id, EmployeeRoleDTO employeeroleDTO);
    void deleteEmployeerole(Long id);
    List<EmployeeRole> deleteAllEmployeeRole();

}
