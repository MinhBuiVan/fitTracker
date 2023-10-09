package com.example.fittracker.model.api.response;

import com.example.fittracker.util.JsonPropertyUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {
    @JsonProperty(value = JsonPropertyUtil.ID)
    private Long activityId;
    @JsonProperty(value = JsonPropertyUtil.MET)
    private BigDecimal met;
    @JsonProperty(value = JsonPropertyUtil.IMAGE)
    private String image;
    @JsonProperty(value = JsonPropertyUtil.NAME)
    private String name;
    @JsonProperty(value = JsonPropertyUtil.TYPE)
    private String type;
}
