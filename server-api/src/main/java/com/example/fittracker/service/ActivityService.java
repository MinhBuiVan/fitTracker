package com.example.fittracker.service;

import com.example.fittracker.exception.ResourceNotFoundException;
import com.example.fittracker.model.Activity;
import com.example.fittracker.model.api.response.ActivityResponse;
import com.example.fittracker.model.base.Pagination;
import com.example.fittracker.model.dto.ActivityDto;
import com.example.fittracker.payload.response.MessageResponse;
import com.example.fittracker.repository.ActivityRepository;
import com.example.fittracker.service.impl.ActivityServiceImpl;
import com.example.fittracker.util.DBParams;
import com.example.fittracker.util.JsonPropertyUtil;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ActivityServiceImpl activityServiceImpl;
    @Autowired
    private ImageService imageService;
    public static Date CalculateFilterTime(String filter) {
        Date startDate = null;
        if (filter.equals("yesterday")) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            startDate = calendar.getTime();
        } else if (filter.equals("today")) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            startDate = calendar.getTime();
        } else if (filter.equals("last 7 days")) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -7);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            startDate = calendar.getTime();
        } else if (filter.equals("last 28 days")) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -28);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            startDate = calendar.getTime();
        } else if (filter.equals("this month")) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            startDate = calendar.getTime();
        } else if (filter.equals("last month")) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            startDate = calendar.getTime();
        }
        return startDate;
    }

    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity getActivityById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public Activity updateActivity(Long id, MultipartFile image, String name, BigDecimal MET, String type) {
        Activity activity = getActivityById(id);
        if (name != null){
            activity.setName(name);
        }
        if (MET != null){
            activity.setMET(MET);
        }
        if (type != null){
            activity.setType(type);
        }
        if (image != null) {
            String imageUrl = imageService.create(image);
            activity.setImage(imageUrl);
        }

        return activityRepository.save(activity);
    }

    public void deleteActivity(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        activityRepository.delete(activity);
    }

    public Activity createActivity(BigDecimal met, String imageURl, String name, String type) {
        Optional<Activity> activity = activityRepository.findActivityByName(name);
        if (activity.isPresent()) {
            throw new IllegalArgumentException("Error: Activity's name is already taken!");
        } else {
            Activity activities = new Activity();
            activities.setMET(met);
            activities.setImage(imageURl);
            activities.setName(name);
            activities.setType(type);
            return activityRepository.save(activities);
        }
    }

    public Pagination<List<ActivityResponse>> getListActivities(String search, String sort, String dir, Integer page, Integer pageSize) {
        Map<String, Sort.Direction> sortDirection = new HashedMap<>();
        sortDirection.put(Sort.Direction.ASC.name(), Sort.Direction.ASC);
        sortDirection.put(Sort.Direction.DESC.name(), Sort.Direction.DESC);
        Map<String, String> sortBy = new HashedMap<>();
        sortBy.put(JsonPropertyUtil.ID, DBParams.L_ID);
        sortBy.put(JsonPropertyUtil.NAME, DBParams.S_NAME);

        Sort orderBy = Sort.by(sortDirection.getOrDefault(dir.toUpperCase(), Sort.Direction.ASC), sortBy.getOrDefault(sort, DBParams.L_ID));

        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), pageSize, orderBy);

        System.out.println("getListActivities page=" + pageable.getPageNumber() + ", page_size=" + pageable.getPageSize() + ", sort=" + pageable.getSort());


        Page<ActivityDto> collections = activityServiceImpl.getListActivities(search.toUpperCase(), pageable);
        List<ActivityResponse> listBaseResponse = collections.getContent().stream()
                .map(o -> ActivityResponse.builder()
                        .activityId(o.getActivityId())
                        .met(o.getMet())
                        .image(o.getImage())
                        .name(o.getName())
                        .type(o.getType())
                        .build())
                .collect(Collectors.toList());

        Pagination<List<ActivityResponse>> response = new Pagination<>(collections.getTotalElements(), page, pageSize, listBaseResponse);

        return response;
    }
}
