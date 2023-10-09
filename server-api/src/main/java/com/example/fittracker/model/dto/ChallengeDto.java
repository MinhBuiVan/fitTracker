package com.example.fittracker.model.dto;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChallengeDto {
    @Id
    @Column(name = DBParams.L_ID)
    private Long challengeId;
    @Column(name = DBParams.D_DATE_START)
    private LocalDateTime dateStart;
    @Column(name = DBParams.D_DATE_END)
    private LocalDateTime dateEnd;
    @Column(name = DBParams.S_DESCRIPTION)
    private String description;
    @Column(name = DBParams.S_NAME)
    private String name;
    @Column(name = DBParams.S_PRIZE)
    private String prize;
    @Column(name = DBParams.S_RULE)
    private String rule;
    @Column(name = DBParams.B_TARGET)
    private BigDecimal target;
}
