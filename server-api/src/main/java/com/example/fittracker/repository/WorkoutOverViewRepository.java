package com.example.fittracker.repository;

import com.example.fittracker.model.dto.WorkoutOverViewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutOverViewRepository extends JpaRepository<WorkoutOverViewDto, Long> {
    @Query(value = "" +
            "SELECT\n" +
            "    sum(tbw.b_distance)        B_TOTAL_DISTANCE ,\n" +
            "    sum(tbw.b_calories_burned) B_TOTAL_CALORIES_BURNED ,\n" +
            "    count(tbw.l_id)            N_TOTAL_WORKOUT \n" +
            "FROM\n" +
            "    tb_workout tbw\n" +
            "    join tb_activity tba ON tba.l_id = tbw.l_activity_id\n" +
            "WHERE " +
            "    tbw.l_user_id = :id \n" +
            "    AND ((:startOfMonth IS NULL AND :endOfMonth IS NULL) OR tbw.d_date BETWEEN :startOfMonth AND :endOfMonth) " +
            "    AND (TRIM(:activityName) IS NULL OR tba.s_name = :activityName) " +
            "ORDER BY tba.s_name" ,
            nativeQuery = true)
    WorkoutOverViewDto getOverViewWorkouts(Long id, String activityName, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
