package com.example.fittracker.model.api.response;

import com.example.fittracker.util.JsonPropertyUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeResponse {
    @JsonProperty(value = JsonPropertyUtil.ID)
    private Long challengeId;
    @JsonProperty(value = JsonPropertyUtil.DATE_START)
    private LocalDateTime dateStart;
    @JsonProperty(value = JsonPropertyUtil.DATE_END)
    private LocalDateTime dateEnd;
    @JsonProperty(value = JsonPropertyUtil.DESCRIPTION)
    private String description;
    @JsonProperty(value = JsonPropertyUtil.NAME)
    private String name;
    @JsonProperty(value = JsonPropertyUtil.PRIZE)
    private String prize;
    @JsonProperty(value = JsonPropertyUtil.RULE)
    private String rule;
    @JsonProperty(value = JsonPropertyUtil.TARGET)
    private BigDecimal target;
}
