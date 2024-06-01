package com.team.controllers;

import com.team.entity.Skills;
import com.team.entity.Team;
import com.team.entity.TeamTheme;
import com.team.repository.TeamThemeRepository;
import com.team.services.TeamServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/teamFinder/team")
public class TeamController {

    private final TeamServiceImpl teamService;
    private final TeamThemeRepository teamThemeRepository;


    public TeamController(TeamServiceImpl teamService, TeamThemeRepository teamThemeRepository) {
        this.teamService = teamService;
        this.teamThemeRepository = teamThemeRepository;
    }

    @GetMapping(value = "/getAllTeams")
    public ResponseEntity<?> getAllTeams(){
        return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);
    }

    @GetMapping(value = "/getTeam/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable Long id){
        return new ResponseEntity<>(teamService.getTeamById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/getTeam/{theme-id}")
    public ResponseEntity<?> getTeamByThemeId(@PathVariable("theme-id") Long themeId){
        return new ResponseEntity<>(teamService.getTeamsByThemeId(themeId), HttpStatus.OK);
    }

    @PostMapping(value = "/saveTeam")
    public ResponseEntity<?> saveTeams(@RequestBody Team team){
        return new ResponseEntity<>(teamService.saveTeam(team), HttpStatus.OK);
    }

    @PostMapping(value = "/saveTheme")
    public ResponseEntity<?> saveTheme(@RequestBody TeamTheme teamTheme){
        return new ResponseEntity<>(teamThemeRepository.save(teamTheme), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllThemes")
    public ResponseEntity<?> getAllThemes(){
        return new ResponseEntity<>(teamThemeRepository.findAll(), HttpStatus.OK);
    }

}
