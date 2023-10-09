package com.example.fittracker.model;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity(name = DBParams.TB_ACTIVITY)
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DBParams.L_ID)
    private Long id;

    @Column(name = DBParams.S_NAME)
    private String name;

    @Column(name = DBParams.S_TYPE)
    private String type;

    @Column(name = DBParams.B_MET)
    @DecimalMin("0.00")
    private BigDecimal MET;

    @Column(name = DBParams.S_IMAGE)
    private String image;
}
