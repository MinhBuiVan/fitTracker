package com.example.fittracker.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String gender;
    private LocalDateTime birthday;
    private BigDecimal weight;
    private BigDecimal height;
    private String image;

    public JwtResponse(String token, Long id, String username, String email, List<String> roles, String firstName, String lastName, String city, String country, String gender, LocalDateTime birthday, BigDecimal weight, BigDecimal height, String image) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.gender = gender;
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.image = image;
    }
}
