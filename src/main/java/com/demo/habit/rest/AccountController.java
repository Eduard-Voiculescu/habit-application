package com.demo.habit.rest;

import com.demo.habit.model.Profile;
import com.demo.habit.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProfile(@RequestBody Profile profile) {
        return profileService.createProfile(profile);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<String> getProfile(@PathVariable String profileId) {
        return profileService.getProfile(profileId);
    }

    @PostMapping("/update/{profileName}")
    public ResponseEntity<String> updateProfile(@PathVariable String profileName) {
        return profileService.updateProfile(profileName);
    }

    @DeleteMapping("/delete/{profileName}")
    public ResponseEntity<String> deleteProfile(@PathVariable String profileName) {
        return profileService.deleteProfile();
    }

}
