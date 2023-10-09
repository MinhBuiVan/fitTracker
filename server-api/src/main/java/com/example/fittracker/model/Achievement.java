package com.example.fittracker.model;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = DBParams.TB_ACHIEVEMENT)
@AllArgsConstructor
@NoArgsConstructor
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DBParams.L_ID)
    private Long id;

    @Column(nullable = false, name = DBParams.S_NAME)
    private String name;

    @Column(name = DBParams.S_DESCRIPTION)
    private String description;

    @Column(nullable = false, name = DBParams.S_IMAGE)
    private String image;

    @Column(nullable = false, name = DBParams.N_TARGET_GOAL)
    private int target;

    @Column(nullable = false, name = DBParams.S_TYPE)
    private String type;

    @Column(nullable = false, name = DBParams.S_ACTIVITY_NAME)
    private String activityName;
}
