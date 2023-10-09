package com.example.fittracker.service;

import com.example.fittracker.exception.ResourceNotFoundException;
import com.example.fittracker.model.Achievement;
import com.example.fittracker.model.Activity;
import com.example.fittracker.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService {
    @Autowired
    private AchievementRepository achievementRepository;
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public Achievement saveAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    public Achievement getAchievementById(Long id) {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public Achievement updateAchievement(Long id, Achievement achievementDetails) {
        Achievement achievement = getAchievementById(id);
        achievement.setName(achievementDetails.getName());
        achievement.setType(achievementDetails.getType());
        achievement.setTarget(achievementDetails.getTarget());
        achievement.setDescription(achievementDetails.getDescription());
        achievement.setImage(achievementDetails.getImage());
        achievement.setActivityName(achievementDetails.getActivityName());
        return achievementRepository.save(achievement);
    }

    public void deleteAchievement(Long id) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        achievementRepository.delete(achievement);
    }
}
