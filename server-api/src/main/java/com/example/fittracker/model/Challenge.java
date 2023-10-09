package com.example.fittracker.model;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = DBParams.TB_CHALLENGE)
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DBParams.L_ID)
    private Long id;

    @Column(nullable = false, name = DBParams.S_NAME)
    private String name;

    @Column(nullable = false, name = DBParams.D_DATE_START)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateStart;

    @Column(nullable = false, name = DBParams.D_DATE_END)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateEnd;

    @Column(nullable = false, name = DBParams.S_DESCRIPTION)
    private String description;

    @Column(nullable = false, name = DBParams.S_RULE)
    private String rule;

    @Column(nullable = false, name = DBParams.S_PRIZE)
    private String prize;

    @Column(nullable = false, name = DBParams.B_TARGET)
    private BigDecimal target;

    @ManyToOne
    @JoinColumn(name = DBParams.L_ACTIVITY_ID)
    //@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Activity activity;

//    @OneToMany(mappedBy = "challenge")
//    private List<ChallengeActivity> challengeActivities = new ArrayList<>();
//
//    @OneToMany(mappedBy = "challenge")
//    private List<UserChallenge> userChallenges = new ArrayList<>();

    @OneToMany(mappedBy = "challenge")
    private List<UserChallenge> users = new ArrayList<>();
}
