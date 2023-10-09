package com.example.fittracker.repository;

import com.example.fittracker.model.dto.ActivityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityOverViewRepository extends JpaRepository<ActivityDto, Long> {
    @Query(value = "" +
            "SELECT\n" +
            "    tba.l_id ,\n" +
            "    tba.b_met ,\n" +
            "    tba.s_image ,\n" +
            "    tba.s_name ,\n" +
            "    tba.s_type \n" +
            "FROM\n" +
            "    tb_activity tba\n" +
            "WHERE " +
            "    (TRIM(:search) IS NULL\n" +
            "    OR TRIM(:search) = ''\n" +
            "    OR UPPER(tba.l_id) LIKE UPPER(:search)\n" +
            "    OR UPPER(tba.s_name) LIKE UPPER(:search) \n" +
            "    OR UPPER(tba.s_type) LIKE UPPER(:search) \n" +
            "    OR UPPER(tba.b_met) LIKE UPPER(:search) )\n",
            countQuery = "SELECT COUNT(*) FROM tb_activity tba\n" +
                    "WHERE " +
                    "    (TRIM(:search) IS NULL\n" +
                    "    OR TRIM(:search) = ''\n" +
                    "    OR UPPER(tba.l_id) LIKE UPPER(:search)\n" +
                    "    OR UPPER(tba.s_name) LIKE UPPER(:search) \n" +
                    "    OR UPPER(tba.s_type) LIKE UPPER(:search) \n" +
                    "    OR UPPER(tba.b_met) LIKE UPPER(:search) )\n",
            nativeQuery = true)
    Page<ActivityDto> getListActivities(String search, Pageable pageable);
}
