package com.example.fittracker.repository;

import com.example.fittracker.model.Workout;
import com.example.fittracker.model.dto.WorkoutDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutDetailRepository extends JpaRepository<WorkoutDetailDto, Long> {
    @Query(value = "" +
            "SELECT\n" +
            "    tbw.l_id ,\n" +
            "    tbw.s_name ,\n" +
            "    tba.s_name S_ACTIVITY_NAME ,\n" +
            "    tbw.b_calories_burned ,\n" +
            "    tbw.b_distance ,\n" +
            "    tbw.b_pace ,\n" +
            "    tbw.d_date ,\n" +
            "    tbw.s_note ,\n" +
            "    tbu.s_first_name ,\n" +
            "    tbu.s_last_name ,\n" +
            "    tba.s_image ,\n" +
            "    TIMESTAMPDIFF(SECOND, tbw.d_time_start, tbw.d_time_end) AS N_DURATION\n" +
            "FROM\n" +
            "    tb_workout tbw\n" +
            "    left outer JOIN tb_activity tba ON tba.l_id = tbw.l_activity_id\n" +
            "    left outer JOIN tb_user tbu ON tbu.l_id = tbw.l_user_id\n" +
            "WHERE " +
            "    tbw.l_id = :id \n" ,
            nativeQuery = true)
    WorkoutDetailDto getWorkoutDetail(Long id);
}
