package com.example.EmpApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.EmpApp.Entity.Skill;
public interface SkillRepository extends JpaRepository<Skill,Long> {
}
