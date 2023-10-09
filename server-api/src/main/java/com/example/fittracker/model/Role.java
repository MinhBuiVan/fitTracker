package com.example.fittracker.model;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = DBParams.TB_ROLE)
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DBParams.L_ID)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = DBParams.S_NAME)
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}
