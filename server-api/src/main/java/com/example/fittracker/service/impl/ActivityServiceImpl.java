package com.example.fittracker.service.impl;

import com.example.fittracker.model.dto.ActivityDto;
import com.example.fittracker.repository.ActivityOverViewRepository;
import com.example.fittracker.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActivityServiceImpl {
    @Autowired
    private ActivityOverViewRepository activityOverViewRepository;

    public Page<ActivityDto> getListActivities(String search, Pageable pageable) {
        return activityOverViewRepository.getListActivities(search, pageable);
    }
}
