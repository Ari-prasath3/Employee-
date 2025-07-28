package com.example.EmpApp.Service;
import com.example.EmpApp.dto.SkillDto;

import java.util.List;

public interface SkillService {
    SkillDto createSkill(SkillDto skillDto);
    SkillDto getSkillById(Long id);
    List<SkillDto> getAllSkills();
    SkillDto updateSkill(Long id,SkillDto skillDto);
    void deleteSkill(Long id);





}
