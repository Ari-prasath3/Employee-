package com.example.EmpApp.Service;

import com.example.EmpApp.dto.EmployeeDTO;
import com.example.EmpApp.Entity.Employee;
import com.example.EmpApp.Entity.Department;
import com.example.EmpApp.Entity.Skill;
import com.example.EmpApp.dto.SkillDto;
import com.example.EmpApp.Repository.SkillRepository;
import com.example.EmpApp.Repository.DepartmentRepository;
import com.example.EmpApp.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Employee toEntity(EmployeeDTO dto) {
        Employee e = new Employee();
        e.setEmployeeId(dto.getEmployeeId());
        e.setEmployeeName(dto.getEmployeeName());
        e.setPhoneNo(dto.getPhoneNo());
        e.setEmail(dto.getEmail());

        if (dto.getPassword() != null) {
            e.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        Department dept = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + dto.getDepartmentId()));
        e.setDepartment(dept);

        if (dto.getSkillIds() != null) {
            List<Skill> skills = skillRepository.findAllById(dto.getSkillIds());
            e.setSkills(skills);
        }

        return e;
    }

    private EmployeeDTO toDTO(Employee e) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(e.getEmployeeId());
        dto.setEmployeeName(e.getEmployeeName());
        dto.setPhoneNo(e.getPhoneNo());
        dto.setEmail(e.getEmail());
        dto.setPassword(null); // Do not expose password
        dto.setDepartmentId(e.getDepartment().getDepartmentId());
        dto.setDepartmentName(e.getDepartment().getDepartmentName());

        if (e.getSkills() != null && !e.getSkills().isEmpty()) {
            List<SkillDto> skillDtos = e.getSkills().stream().map(skill -> {
                SkillDto s = new SkillDto();
                s.setSkillId(skill.getSkillId());
                s.setSkillName(skill.getSkillName());
                return s;
            }).toList();
            dto.setSkills(skillDtos);
        }

        return dto;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public EmployeeDTO getById(Long id) {
        Employee e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
        return toDTO(e);
    }

    @Override
    public EmployeeDTO create(EmployeeDTO dto) {
        Employee saved = repo.save(toEntity(dto));
        return toDTO(saved);
    }

    @Override
    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        Employee e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

        e.setEmployeeName(dto.getEmployeeName());
        e.setPhoneNo(dto.getPhoneNo());
        e.setEmail(dto.getEmail());

        if (dto.getPassword() != null) {
            e.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return toDTO(repo.save(e));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Cannot delete. Employee not found with ID: " + id);
        }
        repo.deleteById(id);
    }
    @Override
    public void resetEmployees(){
        repo.deleteAll();
    }

}
