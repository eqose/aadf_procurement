package com.team.controllers;

import com.team.entity.Ratings;
import com.team.entity.Team;
import com.team.entity.TeamTheme;
import com.team.repository.TeamThemeRepository;
import com.team.services.RatingServiceImpl;
import com.team.services.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/teamFinder/team")
public class TeamController {

    private final TeamServiceImpl teamService;
    private final TeamThemeRepository teamThemeRepository;

    private final RatingServiceImpl ratingService;

    @Autowired
    public TeamController(TeamServiceImpl teamService, TeamThemeRepository teamThemeRepository, RatingServiceImpl ratingService) {
        this.teamService = teamService;
        this.teamThemeRepository = teamThemeRepository;
        this.ratingService = ratingService;
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

    @PostMapping(value = "/ratings/save")
    public ResponseEntity<?> saveRatings(@RequestBody Ratings ratings){
        return new ResponseEntity<>(ratingService.saveRatings(ratings), HttpStatus.OK);
    }

    @GetMapping(value = "/getRatings-team/{teamid}")
    public ResponseEntity<?> getRatingsByTeamId(@PathVariable("teamid") Long teamId){
        return new ResponseEntity<>(ratingService.getRatingsByTeamId(teamId), HttpStatus.OK);
    }
    @GetMapping(value = "/getRatings-user/{userid}")
    public ResponseEntity<?> getRatingsByUserId(@PathVariable("userid") Long userId){
        return new ResponseEntity<>(ratingService.getRatingsByUserId(userId), HttpStatus.OK);
    }
}
