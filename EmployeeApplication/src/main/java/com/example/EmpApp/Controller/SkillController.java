package com.example.EmpApp.Controller;

import com.example.EmpApp.dto.SkillDto;
import com.example.EmpApp.dto.GenericResponseDto;
import com.example.EmpApp.Service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping
    public ResponseEntity<GenericResponseDto<SkillDto>> createSkill(@RequestBody SkillDto skillDTO) {
        SkillDto created = skillService.createSkill(skillDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new GenericResponseDto<>("Skill created successfully", created, true)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseDto<SkillDto>> getSkillById(@PathVariable Long id) {
        SkillDto skill = skillService.getSkillById(id);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Skill retrieved", skill, true)
        );
    }

    @GetMapping
    public ResponseEntity<GenericResponseDto<List<SkillDto>>> getAllSkills() {
        List<SkillDto> skills = skillService.getAllSkills();
        return ResponseEntity.ok(
                new GenericResponseDto<>("All skills retrieved", skills, true)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto<SkillDto>> updateSkill(@PathVariable Long id, @RequestBody SkillDto skillDTO) {
        SkillDto updated = skillService.updateSkill(id, skillDTO);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Skill updated successfully", updated, true)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDto<Void>> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.ok(
                new GenericResponseDto<>("Skill deleted successfully", null, true)
        );
    }
}
