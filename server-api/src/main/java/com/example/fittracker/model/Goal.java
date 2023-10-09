package com.example.fittracker.model;

import com.example.fittracker.service.GoalService;
import com.example.fittracker.util.DBParams;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity(name = DBParams.TB_GOAL)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(GoalService.class)
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DBParams.L_ID)
    private Long id;

    @Column(nullable = false, name = DBParams.D_TIME_START)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStart;

    @Column(nullable = false, name = DBParams.D_TIME_END)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeEnd;

    @Column(nullable = false, name = DBParams.S_TYPE)
    private String type;

    @Column(name = DBParams.S_DESCRIPTION)
    private String description;

    @Column(nullable = false, name = DBParams.N_TARGET_GOAL)
    private int target;

    @Column(nullable = false, name = DBParams.N_CURRENT_COMPLETE)
    private int currentComplete;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = DBParams.L_USER_ID)
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = DBParams.L_ACTIVITY_ID)
    private Activity activity;
}
