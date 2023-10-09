package com.example.fittracker.model.dto;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActivityDto {
    @Id
    @Column(name = DBParams.L_ID)
    private Long activityId;
    @Column(name = DBParams.B_MET)
    private BigDecimal met;
    @Column(name = DBParams.S_IMAGE)
    private String image;
    @Column(name = DBParams.S_NAME)
    private String name;
    @Column(name = DBParams.S_TYPE)
    private String type;
}
