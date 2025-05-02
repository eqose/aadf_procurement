package com.team.services.service_impl;

import com.team.models.User;
import com.team.repository.UserRepository;
import com.team.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    public User create(User user) {
        return repo.save(user);
    }

    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User update(User user) {
        return repo.save(user);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
