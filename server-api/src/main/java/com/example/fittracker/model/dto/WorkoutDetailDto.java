package com.example.fittracker.model.dto;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkoutDetailDto {
    @Id
    @Column(name = DBParams.L_ID)
    private Long workoutId;
    @Column(name = DBParams.S_NAME)
    private String workoutName;
    @Column(name = DBParams.S_ACTIVITY_NAME)
    private String activityName;
    @Column(name = DBParams.B_CALORIES_BURNED)
    private BigDecimal caloriesBurned;
    @Column(name = DBParams.B_DISTANCE)
    private BigDecimal distance;
    @Column(name = DBParams.B_PACE)
    private BigDecimal pace;
    @Column(name = DBParams.D_DATE)
    private LocalDateTime dateTime;
    @Column(name = DBParams.S_NOTE)
    private String note;
    @Column(name = DBParams.S_FIRST_NAME)
    private String firstName;
    @Column(name = DBParams.S_LAST_NAME)
    private String lastName;
    @Column(name = DBParams.S_IMAGE)
    private String imageActivity;
    @Column(name = DBParams.N_DURATION)
    private int duration;
}
