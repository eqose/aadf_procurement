package com.team.services;

import com.team.entity.Skills;
import com.team.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsServiceImpl {

    private final SkillsRepository skillsRepository;

    @Autowired
    public SkillsServiceImpl(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }


    List<Skills> getAllSkills(){
        return this.skillsRepository.findAll();
    }

    List<Skills>getAllSkillsByID(Long id){
        return this.skillsRepository.findByUser_Id(id);
    }

}
