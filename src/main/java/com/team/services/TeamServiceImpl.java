package com.team.services;

import com.team.entity.Team;
import com.team.entity.UserInformation;
import com.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    List<Team> getAllTeams(){
        return this.teamRepository.findAll();
    }

    Team saveTeam (Team team){
        return this.teamRepository.save(team);
    }
    Team getTeamById (Long id){
        return this.teamRepository.findById(id).get();
    }
    List<Team> getTeamsByUserId (Long userId){
        return this.teamRepository.getTeamsByUserId(userId);
    }
}
