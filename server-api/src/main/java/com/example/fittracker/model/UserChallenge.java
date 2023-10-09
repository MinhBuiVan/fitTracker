package com.example.fittracker.model;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = DBParams.TB_USER_CHALLENGE)
@AllArgsConstructor
@NoArgsConstructor

@IdClass(UserChallenge.UserChallengeId.class)
public class UserChallenge {
    @Data
    public static class UserChallengeId implements Serializable {
        private Long user;
        private Long challenge;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = DBParams.L_USER_ID)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = DBParams.L_CHALLENGE_ID)
    private Challenge challenge;

    @DecimalMin("0.00")
    @DecimalMax("100")
    @Column(name = DBParams.B_PROGRESS)
    private BigDecimal progress;

    public UserChallenge(User user, Challenge challenge) {
        this.user = user;
        this.challenge = challenge;
    }
}
