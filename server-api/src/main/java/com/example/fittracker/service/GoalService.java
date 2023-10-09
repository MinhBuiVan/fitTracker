package com.example.fittracker.service;

import com.example.fittracker.exception.ResourceNotFoundException;
import com.example.fittracker.model.Activity;
import com.example.fittracker.model.Goal;
import com.example.fittracker.model.User;
import com.example.fittracker.payload.workout.CreateGoalRequest;
import com.example.fittracker.repository.ActivityRepository;
import com.example.fittracker.repository.GoalRepository;
import com.example.fittracker.repository.UserRepository;
import com.example.fittracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoalService {

    private GoalRepository goalRepository;
    private WorkoutRepository workoutRepository;
    private UserRepository userRepository;
    private ActivityRepository activityRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository, WorkoutRepository workoutRepository,
                       UserRepository userRepository, ActivityRepository activityRepository) {
        this.goalRepository = goalRepository;
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    public GoalService() {

    }

    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    public Goal saveGoal(Goal goal) {
        return goalRepository.save(goal);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Goal createGoal(CreateGoalRequest request) {
        Goal goal = new Goal();
        goal.setTarget(request.getTarget());
        goal.setTimeStart(request.getTimeStart());
        goal.setTimeEnd(request.getTimeEnd());
        goal.setType(request.getType());
        goal.setDescription(request.getDescription());

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid activity ID"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        goal.setActivity(activity);
        goal.setUser(user);
        calculateCurrentComplete(goal);
        return goalRepository.save(goal);
    }

    public Goal getGoalById(Long id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goal", "id", id));
    }

    public void deleteGoal(Long id) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goal", "id", id));
        goalRepository.delete(goal);
    }

    public List<Goal> getGoalsByUserId(Long userId) {
        List<Goal> goals = goalRepository.findAllByUserId(userId);
        for (Goal goal : goals) {
            calculateCurrentComplete(goal);
            goalRepository.save(goal);
        }
        return goals;
    }

    private void calculateCurrentComplete(Goal goal) {
        Long activityId = goal.getActivity().getId();
        Long userId = goal.getUser().getId();
        LocalDateTime timeStart = goal.getTimeStart();
        LocalDateTime timeEnd = goal.getTimeEnd();

        int completedCount;
        if (goal.getType().equals("numberOfWorkouts")) {
            completedCount = workoutRepository.countByUserIdAndActivityIdAndTimeRange(userId, activityId, timeStart, timeEnd);
        } else if (goal.getType().equals("distance")) {
            BigDecimal totalDistance = workoutRepository.sumDistanceByUserIdAndActivityIdAndTimeRange(userId, activityId, timeStart, timeEnd);
            completedCount = totalDistance != null ? totalDistance.intValue() : 0;
        } else {
            completedCount = 0;
        }

        int target = goal.getTarget();
        if (target > 0) {
            int currentComplete = Math.min((completedCount * 100) / target, 100);
            goal.setCurrentComplete(currentComplete);
        }
    }
}