package com.example.fittracker.payload.workout;

import java.time.LocalDateTime;

public class CreateGoalRequest {
    private Long userId;
    private Long activityId;
    private String type;
    private int target;
    private String description;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

    public CreateGoalRequest() {
    }

    public CreateGoalRequest(Long userId, Long activityId, String type, int target, String description, LocalDateTime timeStart, LocalDateTime timeEnd) {
        this.userId = userId;
        this.activityId = activityId;
        this.type = type;
        this.target = target;
        this.description = description;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
