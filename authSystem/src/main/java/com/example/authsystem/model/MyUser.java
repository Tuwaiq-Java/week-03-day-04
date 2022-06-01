package com.example.authsystem.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Username is required")
    @Column(unique = true)
    @Size(min = 4,max = 15,message = "Your username must be more than 4 and less than 15")
    private String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 3,max = 10,message = "Your password must be more than 3 and less than 10")
    private String password;
    @NotEmpty(message = "Role is required")
    @Pattern(regexp = "(ADMIN|USER)",message = "Role must be in (ADMIN|USER)")
    private String role;
    @NotEmpty(message = "Email is required")
    @Email(message = "Please send valid email")
    @Column(unique = true)
    private String email;
}
