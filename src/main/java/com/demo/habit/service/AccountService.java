package com.demo.habit.service;

import com.demo.habit.model.Profile;
import com.demo.habit.repository.HabitProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProfileService {

    private final HabitProfileRepository habitProfileRepository;

    public ProfileService(HabitProfileRepository habitProfileRepository) {
        this.habitProfileRepository = habitProfileRepository;
    }

    public ResponseEntity<String> createProfile(Profile profile) {
        if (habitProfileRepository.existsById(profile.getId())) {
            log.debug("Cannot create profile {} as it already exist.", profile.getId());
            return ResponseEntity.badRequest().body(
                    String.format("Profile %s already exists.", profile.getId())
            );
        } else {
            log.debug("Profile {} created successfully", profile.getId());
            habitProfileRepository.save(profile);
            return ResponseEntity.ok("Profile creaxted successfully");

        }
    }

    public ResponseEntity<String> getProfile(String profileId) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> updateProfile(String profileName) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> deleteProfile() {
        return ResponseEntity.ok().build();
    }

}
