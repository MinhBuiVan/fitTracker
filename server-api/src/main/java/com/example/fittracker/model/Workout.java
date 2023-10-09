package com.example.fittracker.model;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = DBParams.TB_WORKOUT)
@AllArgsConstructor
@NoArgsConstructor
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DBParams.L_ID)
    private Long id;

    @Column(nullable = false, name = DBParams.S_NAME)
    private String name;

    @Column(nullable = false, name = DBParams.D_TIME_START)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStart;

    @Column(nullable = false, name = DBParams.D_TIME_END)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeEnd;

    @Column(nullable = false, name = DBParams.D_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    @Column(nullable = false, name = DBParams.B_DISTANCE)
    private BigDecimal distance;

    @Column(nullable = false, name = DBParams.B_PACE)
    @DecimalMin("0.00")
    private BigDecimal pace;

    @Column(name = DBParams.S_NOTE)
    private String note;

    @Column(nullable = false, name = DBParams.B_CALORIES_BURNED)
    @DecimalMin("0.00")
    private BigDecimal caloriesBurned;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = DBParams.L_USER_ID)
    //@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = DBParams.L_ACTIVITY_ID)
    //@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Activity activity;
}
