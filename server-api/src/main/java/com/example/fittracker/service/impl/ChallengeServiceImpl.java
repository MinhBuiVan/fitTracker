package com.example.fittracker.service.impl;

import com.example.fittracker.model.dto.ChallengeDto;
import com.example.fittracker.repository.ChallengeOverViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl {
    @Autowired
    private ChallengeOverViewRepository challengeOverViewRepository;
    public Page<ChallengeDto> getListChallenges(String search, Pageable pageable) {
        return challengeOverViewRepository.getListChallenges(search, pageable);
    }
}
