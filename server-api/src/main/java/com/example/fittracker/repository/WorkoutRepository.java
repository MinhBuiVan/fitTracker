package com.example.fittracker.repository;

import com.example.fittracker.model.Workout;
import com.example.fittracker.model.dto.WorkoutDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    @Query("SELECT w FROM TB_WORKOUT w " +
            "WHERE w.timeStart >= :startTime AND w.timeEnd <= :endTime")
    List<Workout> findWorkoutsByTimeRange(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    long countWorkoutsByUserIdAndDateTimeBetween(Long userId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("SELECT SUM(w.caloriesBurned) FROM TB_WORKOUT w WHERE w.user.id = :userId AND w.dateTime BETWEEN :startDateTime AND :endDateTime")
    BigDecimal sumCaloriesBurnedByUser_UserIdAndDateTimeBetween(
            @Param("userId") Long userId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );

    @Query("SELECT SUM(w.distance) FROM TB_WORKOUT w WHERE w.user.id = :userId AND w.dateTime BETWEEN :startDateTime AND :endDateTime")
    BigDecimal sumDistanceByUser_UserIdAndDateTimeBetween(
            @Param("userId") Long userId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );
    // goal and workout
    @Query("SELECT tbw FROM TB_WORKOUT tbw " +
            "WHERE tbw.dateTime >= :startTime AND tbw.dateTime <= :endTime AND tbw.user.id = :userId"
    )
    List<Workout> findByUserIdAndDateTimeBetween(Long userId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT COUNT(w) FROM TB_WORKOUT w WHERE w.user.id = :userId AND w.activity.id = :activityId AND w.timeStart >= :timeStart AND w.timeEnd <= :timeEnd")
    int countByUserIdAndActivityIdAndTimeRange(@Param("userId") Long userId, @Param("activityId") Long activityId, @Param("timeStart") LocalDateTime timeStart, @Param("timeEnd") LocalDateTime timeEnd);

    @Query("SELECT SUM(w.distance) FROM TB_WORKOUT w WHERE w.user.id = :userId AND w.activity.id = :activityId AND w.timeStart >= :timeStart AND w.timeEnd <= :timeEnd")
    BigDecimal sumDistanceByUserIdAndActivityIdAndTimeRange(@Param("userId") Long userId, @Param("activityId") Long activityId, @Param("timeStart") LocalDateTime timeStart, @Param("timeEnd") LocalDateTime timeEnd);
    @Query("SELECT w FROM TB_WORKOUT w WHERE w.user.id = :userId AND w.timeStart >= :timeStart AND w.timeEnd <= :timeEnd")
    List<Workout> findByUser_UserIdAndDateTime(@Param("userId") Long userId, @Param("timeStart") LocalDateTime timeStart, @Param("timeEnd") LocalDateTime timeEnd);

    long countWorkoutsByUserId(Long userId);
    @Query("SELECT SUM(w.caloriesBurned) FROM TB_WORKOUT w WHERE w.user.id = :userId")
    BigDecimal sumCaloriesBurnedByUser_UserId(@Param("userId") Long userId);
    @Query("SELECT SUM(w.distance) FROM TB_WORKOUT w WHERE w.user.id = :userId")
    BigDecimal sumDistanceByUser_UserId(@Param("userId") Long userId);

    List<Workout> findByUserId(Long userId);
    @Query("SELECT COALESCE(SUM(TIME_TO_SEC(TIMEDIFF(w.timeEnd, w.timeStart))), 0) FROM TB_WORKOUT w WHERE w.user.id = :userId AND w.dateTime BETWEEN :startDateTime AND :endDateTime")
    BigDecimal calculateTotalTimeByUser_UserIdAndDateTimeBetween(
            @Param("userId") Long userId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );
}

