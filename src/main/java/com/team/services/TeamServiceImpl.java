package com.team.services;

import com.team.entity.Team;
import com.team.entity.UserInformation;
import com.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams(){
        return this.teamRepository.findAll();
    }

    public Team saveTeam (Team team){
        return this.teamRepository.save(team);
    }
    public Team getTeamById (Long id){
        return this.teamRepository.findById(id).get();
    }
    public List<Team> getTeamsByUserId (Long userId){
        return this.teamRepository.getTeamsByUserId(userId);
    }
    public List<Team> getTeamsByThemeId (Long themeId){
        return this.teamRepository.getTeamsByTheme_Id(themeId);
    }
}
