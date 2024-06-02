package com.team.services;

import com.team.entity.Team;
import com.team.entity.TeamMembers;
import com.team.entity.User;
import com.team.repository.TeamMembersRepository;
import com.team.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private TeamServiceImpl teamService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamMembersRepository teamMembersRepository;


    /**
     * @param username
     * @param teamId
     * @return
     */
    public ResponseEntity<?> joinTeam(String username, Long teamId) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

            Team team = this.teamService.getTeamById(teamId);
            TeamMembers teamMembers = new TeamMembers();
            teamMembers.setUser(user);
            teamMembers.setTeam(team);
            this.teamMembersRepository.save(teamMembers);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param username
     * @param teamId
     * @return
     */
    public ResponseEntity<?> leaveTeams(String username, Long teamId) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
            Team team = this.teamService.getTeamById(teamId);
            TeamMembers byUserIdAndTeamId = this.teamMembersRepository.findByUser_IdAndTeam_Id(user.getId(), team.getId());
            if (byUserIdAndTeamId != null) {
                teamMembersRepository.deleteById(byUserIdAndTeamId.getId());
                return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You are not part of this course.", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param username
     * @return
     */
    public ResponseEntity<?> getTeamsByUser(String username) {
        List<Team> teams = new ArrayList<>();
        try {
            List<User> user = userRepository.findByUsernameLike("%" + username + "%");
            for (User u : user) {
                List<Team> t = this.teamService.getTeamsByUserId(u.getId());
                teams.addAll(t);
            }
            return new ResponseEntity<>(teams, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
