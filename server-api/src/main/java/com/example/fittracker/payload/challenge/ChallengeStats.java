package com.example.fittracker.payload.challenge;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChallengeStats {
    private String progress;
    private String totalTime;
    private int caloriesBurned;
    private int numWorkouts;
}
