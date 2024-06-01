package com.team.controllers;

import com.team.entity.ERole;
import com.team.entity.Role;
import com.team.entity.User;
import com.team.repository.RoleRepository;
import com.team.repository.UserRepository;
import com.team.security.jwt.JwtUtils;
import com.team.security.request.LoginRequest;
import com.team.security.request.RegisterRequest;
import com.team.security.response.JwtResponse;
import com.team.security.response.MessageResponse;
import com.team.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/teamFinder/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles.get(0)));
    }

    /**
     * @param registerRequest
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        try {
            InternetAddress emailAddr = new InternetAddress(registerRequest.getEmail());
            emailAddr.validate();
        } catch (AddressException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(ex.getMessage()));
        }
        User user = new User(registerRequest.getUsername(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()));

        String strRoles = registerRequest.getRole();
        Role role = new Role();

        if (strRoles == null) {
            role = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else {
                if ("ADMIN".equals(strRoles)) {
                    role = roleRepository.findByName(ERole.ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                } else if ("USER".equals((strRoles))) {
                    role = roleRepository.findByName(ERole.USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                }

        }
        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}
