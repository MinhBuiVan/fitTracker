package com.example.fittracker.payload.workout;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateWorkoutRequest {
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private Long activityId;
    private Long userId;
    private String name;
    private LocalDateTime dateTime;
    private BigDecimal distance;
    private String note;
    private BigDecimal pace;

    public CreateWorkoutRequest() {
    }

    public CreateWorkoutRequest(LocalDateTime timeStart, LocalDateTime timeEnd, Long activityId, Long userId, String name, LocalDateTime dateTime, BigDecimal distance, String note, BigDecimal pace) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.activityId = activityId;
        this.userId = userId;
        this.name = name;
        this.dateTime = dateTime;
        this.distance = distance;
        this.note = note;
        this.pace = pace;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getPace() {
        return pace;
    }

    public void setPace(BigDecimal pace) {
        this.pace = pace;
    }
}
