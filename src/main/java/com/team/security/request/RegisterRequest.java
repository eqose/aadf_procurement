package com.team.security.request;

import com.team.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
    @NotNull
    @Size(min = 3,max = 20)
    private String username;

    @NotNull
    @Size(max=30)
    private String email;

    private String firstName;

    private String lastName;

    private String role;

    @NotNull
    @Size(max=120)
    private String password;


}
