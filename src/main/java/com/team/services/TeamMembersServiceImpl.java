package com.team.services;

import com.team.entity.TeamMembers;
import com.team.entity.UserInformation;
import com.team.repository.TeamMembersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMembersServiceImpl {

    private final TeamMembersRepository teamMembersRepository;

    public TeamMembersServiceImpl(TeamMembersRepository teamMembersRepository) {
        this.teamMembersRepository = teamMembersRepository;
    }

    List<TeamMembers> getAllTeamMembers(){
        return this.teamMembersRepository.findAll();
    }

    TeamMembers saveTeamMembers (TeamMembers teamMembers){
        return this.teamMembersRepository.save(teamMembers);
    }
}
