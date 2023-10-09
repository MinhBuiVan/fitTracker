package com.example.fittracker.repository;

import com.example.fittracker.model.Goal;
import com.example.fittracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findAllByUserId(Long userId);
//    @Query("SELECT g FROM Goal g WHERE g.user.userId = :userId")
//    List<Goal> findGoalsByUser_UserId(Long userId);
}
