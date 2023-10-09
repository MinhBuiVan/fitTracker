package com.example.fittracker.model.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;

public class ActivityCreateDto implements Serializable {
    private Long id;
    private BigDecimal met;
    private MultipartFile image;
    private String name;
    private String type;
}
