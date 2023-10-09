package com.example.fittracker.payload.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class SignupRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime birthday;
    private String country;
    private String city;
    private String gender;
    private Set<String> role;
    private String image;
}
