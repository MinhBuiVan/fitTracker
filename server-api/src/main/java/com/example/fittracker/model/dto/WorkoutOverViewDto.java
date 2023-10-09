package com.example.fittracker.model.dto;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkoutOverViewDto {
    @Id
    @Column(name = DBParams.B_TOTAL_DISTANCE)
    private BigDecimal totalDistance;
    @Column(name = DBParams.B_TOTAL_CALORIES_BURNED)
    private BigDecimal totalCaloriesBurned;
    @Column(name = DBParams.N_TOTAL_WORKOUT)
    private Integer totalWorkout;
}
