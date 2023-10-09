package com.example.fittracker.model;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = DBParams.TB_USER)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = DBParams.S_USERNAME),
                @UniqueConstraint(columnNames = DBParams.S_EMAIL)
        })
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DBParams.L_ID)
    private Long id;

    @Column(nullable = false, name = DBParams.S_FIRST_NAME)
    @NotEmpty(message = "First_name is not null")
    private String firstName;

    @Column(nullable = false, name = DBParams.S_LAST_NAME)
    @NotEmpty(message = "Last_name is not null")
    private String lastName;

    @Column(nullable = false, name = DBParams.S_USERNAME)
    private String username;

    @Column(nullable = false, name = DBParams.S_EMAIL)
    @Email(message = "Invalid email format")
    private String email;

    @Column(nullable = false, name = DBParams.S_PASSWORD)
    @NotEmpty(message = "Password is not null")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$")
//    @Pattern(regexp = "^[a-zA-Z0-9]{8,16}$")
//    @Pattern(regexp = "^[a-zA-Z0-9]{6,}$")
    private String password;
    @Column(name = DBParams.S_GENDER)
    private String gender;
    @Column(name = DBParams.D_BIRTHDAY)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;
    @Column(name = DBParams.S_CITY)
    private String city;
    @Column(name = DBParams.S_COUNTRY)
    private String country;

    @Column(name = DBParams.B_WEIGHT)
    @DecimalMin("0.01")
    private BigDecimal weight;

    @Column(name = DBParams.B_HEIGHT)
    @DecimalMin("0.01")
    private BigDecimal height;

    @Column(columnDefinition = "varchar(255) default 'blank-profile-picture.png'", name = DBParams.S_IMAGE)
    private String image;

    @ManyToMany
    @JoinTable(
            name = DBParams.TB_USER_FRIEND,
            joinColumns = @JoinColumn(name = DBParams.L_USER_ID),
            inverseJoinColumns = @JoinColumn(name = DBParams.L_FRIEND_ID)
    )
    private List<User> friends = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserChallenge> challenges = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.LAZY)
    @ManyToMany
    @JoinTable(name = DBParams.TB_USER_ROLE,
            joinColumns = @JoinColumn(name = DBParams.L_USER_ID),
            inverseJoinColumns = @JoinColumn(name = DBParams.L_ROLE_ID))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = DBParams.TB_USER_ACHIEVEMENT,
            joinColumns = @JoinColumn(name = DBParams.L_USER_ID),
            inverseJoinColumns = @JoinColumn(name = DBParams.L_ACHIEVEMENT_ID)
    )
    private List<Achievement> achievements = new ArrayList<>();

    public User(String firstName, String lastName, String username, String email, String password, LocalDateTime birthday, String gender, String country, String city, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.country = country;
        this.city = city;
        this.image = image;
    }
}
