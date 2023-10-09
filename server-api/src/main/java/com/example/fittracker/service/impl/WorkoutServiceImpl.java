package com.example.fittracker.service.impl;

import com.example.fittracker.model.dto.WorkoutDetailDto;
import com.example.fittracker.model.dto.WorkoutOverViewDto;
import com.example.fittracker.repository.WorkoutDetailRepository;
import com.example.fittracker.repository.WorkoutOverViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkoutServiceImpl {
    @Autowired
    private WorkoutOverViewRepository workoutOverViewRepository;
    @Autowired
    private WorkoutDetailRepository workoutDetailRepository;
    public WorkoutOverViewDto getOverViewWorkouts(Long id, String activityName, LocalDateTime startOfMonth, LocalDateTime endOfMonth) {
        return workoutOverViewRepository.getOverViewWorkouts(id, activityName, startOfMonth, endOfMonth);
    }

    public WorkoutDetailDto getWorkoutDetail(Long id) {
        return workoutDetailRepository.getWorkoutDetail(id);
    }
}
