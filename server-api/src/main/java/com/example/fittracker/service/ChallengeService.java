package com.example.fittracker.service;

import com.example.fittracker.exception.ResourceNotFoundException;
import com.example.fittracker.model.Activity;
import com.example.fittracker.model.Challenge;
import com.example.fittracker.model.api.response.ActivityResponse;
import com.example.fittracker.model.api.response.ChallengeResponse;
import com.example.fittracker.model.base.Pagination;
import com.example.fittracker.model.dto.ActivityDto;
import com.example.fittracker.model.dto.ChallengeDto;
import com.example.fittracker.repository.ActivityRepository;
import com.example.fittracker.repository.ChallengeRepository;
import com.example.fittracker.service.impl.ChallengeServiceImpl;
import com.example.fittracker.util.DBParams;
import com.example.fittracker.util.JsonPropertyUtil;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private ChallengeServiceImpl challengeServiceImpl;
    @Autowired
    private ActivityRepository activityRepository;

    public Challenge saveChallenge(Challenge challenge) {
        Challenge challenge1 = new Challenge();
        challenge1.setDateStart(challenge.getDateStart());
        challenge1.setDateEnd(challenge.getDateEnd());
        challenge1.setDescription(challenge.getDescription());
        challenge1.setName(challenge.getName());
        challenge1.setPrize(challenge.getPrize());
        challenge1.setRule(challenge.getRule());
        challenge1.setTarget(challenge.getTarget());

        Activity activity = activityRepository.findById(challenge.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid activity ID"));

        challenge1.setActivity(activity);
        return challengeRepository.save(challenge1);
    }

    public Challenge getChallengeById(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public Challenge updateChallenge(Long id, Challenge challengeDetails) {
        Challenge challenge = getChallengeById(id);
        challenge.setName(challengeDetails.getName());
        challenge.setDateStart(challengeDetails.getDateStart());
        challenge.setDateEnd(challengeDetails.getDateEnd());
        challenge.setDescription(challengeDetails.getDescription());
        challenge.setRule(challengeDetails.getRule());
        challenge.setPrize(challengeDetails.getPrize());
        challenge.setTarget(challengeDetails.getTarget());
        return challengeRepository.save(challenge);
    }

    public void deleteChallenge(Long id) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        challengeRepository.delete(challenge);
    }

    public Pagination<List<ChallengeResponse>> getListChallenges(String search, String sort, String dir, Integer page, Integer pageSize) {
        Map<String, Sort.Direction> sortDirection = new HashedMap<>();
        sortDirection.put(Sort.Direction.ASC.name(), Sort.Direction.ASC);
        sortDirection.put(Sort.Direction.DESC.name(), Sort.Direction.DESC);
        Map<String, String> sortBy = new HashedMap<>();
        sortBy.put(JsonPropertyUtil.ID, DBParams.L_ID);
        sortBy.put(JsonPropertyUtil.NAME, DBParams.S_NAME);

        Sort orderBy = Sort.by(sortDirection.getOrDefault(dir.toUpperCase(), Sort.Direction.ASC), sortBy.getOrDefault(sort, DBParams.L_ID));

        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), pageSize, orderBy);

        System.out.println("getListChallenges page=" + pageable.getPageNumber() + ", page_size=" + pageable.getPageSize() + ", sort=" + pageable.getSort());


        Page<ChallengeDto> collections = challengeServiceImpl.getListChallenges(search.toUpperCase(), pageable);
        List<ChallengeResponse> listBaseResponse = collections.getContent().stream()
                .map(o -> ChallengeResponse.builder()
                        .challengeId(o.getChallengeId())
                        .dateStart(o.getDateStart())
                        .dateEnd(o.getDateEnd())
                        .description(o.getDescription())
                        .name(o.getName())
                        .prize(o.getPrize())
                        .rule(o.getRule())
                        .target(o.getTarget())
                        .build())
                .collect(Collectors.toList());

        Pagination<List<ChallengeResponse>> response = new Pagination<>(collections.getTotalElements(), page, pageSize, listBaseResponse);

        return response;
    }
}
