package com.team.controllers;

import com.team.entity.*;
import com.team.repository.RoleRepository;
import com.team.repository.UserRepository;
import com.team.services.SkillsServiceImpl;
import com.team.services.UserInformationService;
import com.team.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/teamFinder/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInformationService userInformationService;
    @Autowired
    private SkillsServiceImpl skillsService;
    @Autowired
    private RoleRepository roleRepository;


//    @PostConstruct
//    public void generateAdmin() {
//        User user=new User();
//        user.setUsername("admin");
//        user.setPassword(encoder.encode("admin"));
//        user.setEmail("admin@admin.com");
//        Role role=roleRepository.findByName(ERole.ADMIN).isPresent() ? roleRepository.findByName(ERole.ADMIN).get() : new Role(ERole.ADMIN);
//        user.setRole(role);
//        !roleRepository.findByName(ERole.ADMIN).isPresent() ? roleRepository.save(role);
//        if(!userRepository.existsByUsername("admin")){
//            userRepository.saveAndFlush(user);
//        }
//}

    /**
     * @param username
     * @param teamId
     * @return
     */
    @PostMapping(value = "/joinTeam")
    public ResponseEntity<?> joinTeam(@Param(value = "username") String username, @Param(value = "teamId") Long teamId){
        return userService.joinTeam(username, teamId);
    }

    /**
     * @param username
     * @param teamId
     * @return
     */
    @PostMapping(value = "/leaveTeam/username")
    public ResponseEntity<?> leaveTeam(@Param(value = "username") String username, @Param(value = "teamId") Long teamId){
        return userService.leaveTeams(username, teamId);
    }

    /**
     * @param username
     * @return
     */
    @GetMapping(value = "/allTeams/username/{username}")
    public ResponseEntity<?> getTeamsByUser(@PathVariable String username){
        return userService.getTeamsByUser(username);
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping(value = "/getUser/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userRepository.findById(userId).get(), HttpStatus.OK);
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping(value = "/getUserInfo/{userId}")
    public ResponseEntity<?> getUserInfoById(@PathVariable Long userId){
        return new ResponseEntity<>(userInformationService.getUserInfoById(userId), HttpStatus.OK);
    }


    @GetMapping(value = "/saveUserInfo")
    public ResponseEntity<?> saveUserInfo(@RequestBody UserInformation user){
        return new ResponseEntity<>(userInformationService.saveUserInfo(user), HttpStatus.OK);
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping(value = "/getSkills/{userId}")
    public ResponseEntity<?> getSkillsByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(skillsService.getAllSkillsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/saveSkills")
    public ResponseEntity<?> saveSkills(@RequestBody Skills skills){
        return new ResponseEntity<>(skillsService.saveSkills(skills), HttpStatus.OK);
    }
    /**
     * @param username
     * @return
     */
    @GetMapping(value = "/getUserByUsername/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(userRepository.findByUsername(username).get(), HttpStatus.OK);
    }
//
//    /**
//     * @param predicate
//     * @param pageable
//     * @return
//     */
//    @GetMapping(value = "/all")
//    public ResponseEntity<Page<User>> getAllUsers(@QuerydslPredicate Predicate predicate,
//                                                  @PageableDefault(sort = {"username"}, direction = Sort.Direction.DESC) Pageable pageable) {
//        if(predicate==null){
//            return new ResponseEntity<>(userRepository.findAll(pageable),HttpStatus.OK);
//        }
//        return new ResponseEntity<>(userRepository.findAll(predicate,pageable),HttpStatus.OK);
//    }

    /**
     * @param userId
     * @param updatedUser
     * @return
     */
    @PostMapping(value= "/updateUser/{userId}")
    public ResponseEntity<?> editUser(@PathVariable Long userId,@RequestBody User updatedUser){
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()){
            if(!user.get().getUsername().equals(updatedUser.getUsername()) && userRepository.findByUsername(updatedUser.getUsername()).isPresent()) {
                    return new ResponseEntity<>("This username is taken!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userRepository.save(updatedUser), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
