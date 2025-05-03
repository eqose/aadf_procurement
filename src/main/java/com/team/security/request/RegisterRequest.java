package com.team.security.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String role;

    private String password;


}
