package com.team.controllers;

import com.team.models.User;
import com.team.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping
    public List<User> list() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> get(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public Optional<User> getByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
