package com.example.fittracker.model;

import com.example.fittracker.util.DBParams;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity(name = DBParams.TB_COMMENT)
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DBParams.L_ID)
    private Long id;

    @Column(nullable = false, name = DBParams.S_CONTENT)
    private String content;

    @Column(nullable = false, name = DBParams.D_CREATE)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = DBParams.L_POST_ID)
    //@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Post post;
}
