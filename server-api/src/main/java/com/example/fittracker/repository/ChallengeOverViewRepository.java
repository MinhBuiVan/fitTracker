package com.example.fittracker.repository;

import com.example.fittracker.model.dto.ChallengeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeOverViewRepository extends JpaRepository<ChallengeDto, Long> {
    @Query(value = "" +
            "SELECT\n" +
            "    tbc.l_id ,\n" +
            "    tbc.d_date_start ,\n" +
            "    tbc.d_date_end ,\n" +
            "    tbc.s_description ,\n" +
            "    tbc.s_name ,\n" +
            "    tbc.s_prize ,\n" +
            "    tbc.s_rule ,\n" +
            "    tbc.b_target \n" +
            "FROM\n" +
            "    tb_challenge tbc\n" +
            "WHERE " +
            "    (TRIM(:search) IS NULL\n" +
            "    OR TRIM(:search) = ''\n" +
            "    OR UPPER(tbc.l_id) LIKE UPPER(:search)\n" +
            "    OR UPPER(tbc.s_name) LIKE UPPER(:search) )\n",
            countQuery = "SELECT COUNT(*) FROM tb_challenge tbc\n" +
                    "WHERE " +
                    "    (TRIM(:search) IS NULL\n" +
                    "    OR TRIM(:search) = '%%'\n" +
                    "    OR UPPER(tbc.l_id) LIKE UPPER(:search)\n" +
                    "    OR UPPER(tbc.s_name) LIKE UPPER(:search) )\n",
            nativeQuery = true)
    Page<ChallengeDto> getListChallenges(String search, Pageable pageable);
}
