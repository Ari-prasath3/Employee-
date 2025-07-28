package com.example.EmpApp.Service;
import com.example.EmpApp.Entity.Skill;
import com.example.EmpApp.Entity.Employee;
import com.example.EmpApp.Repository.EmployeeRepository;
import com.example.EmpApp.Repository.SkillRepository;
import com.example.EmpApp.dto.SkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public SkillDto createSkill(SkillDto skillDto){
        Skill skill = new Skill();
        skill.setSkillName(skillDto.getSkillName());

        if (skillDto.getEmployeeIds() != null) {
            List<Employee> employees = employeeRepository.findAllById(skillDto.getEmployeeIds());
            skill.setEmployees(employees);
        }

        Skill savedSkill = skillRepository.save(skill);
        return convertToDTO(savedSkill);

    }
    @Override
    public SkillDto getSkillById(Long id) {
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found"));
        return convertToDTO(skill);
    }

    @Override
    public List<SkillDto> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SkillDto updateSkill(Long id, SkillDto skillDTO) {
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found"));
        skill.setSkillName(skillDTO.getSkillName());

        if (skillDTO.getEmployeeIds() != null) {
            List<Employee> employees = employeeRepository.findAllById(skillDTO.getEmployeeIds());
            skill.setEmployees(employees);
        }

        Skill updated = skillRepository.save(skill);
        return convertToDTO(updated);
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    private SkillDto convertToDTO(Skill skill) {
        SkillDto dto = new SkillDto();
        dto.setSkillId(skill.getSkillId());
        dto.setSkillName(skill.getSkillName());
        if (skill.getEmployees() != null) {
            dto.setEmployeeIds(skill.getEmployees()
                    .stream()
                    .map(emp -> emp.getEmployeeId())
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}




