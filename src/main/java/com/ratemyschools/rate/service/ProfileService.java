package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Profile.AddProfileDto;
import com.ratemyschools.rate.model.Profile;
import com.ratemyschools.rate.model.User;
import com.ratemyschools.rate.repository.ProfileRepository;
import com.ratemyschools.rate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public Profile addProfile(AddProfileDto dto) {
        try {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            Profile profile = profileRepository.findByUserId(dto.getUserId())
                    .orElseGet(() -> {
                        Profile p = new Profile();
                        p.setUser(user);
                        return p;
                    });

            profile.setLocation(dto.getLocation());
            profile.setMajor(dto.getMajor());
            profile.setAcademicLevel(dto.getAcademicLevel());
            profile.setCampusSetting(dto.getCampusSetting());
            profile.setFinance(dto.getFinance());
            profile.setGoals(dto.getGoals());
            profile.setLiving(dto.getLiving());
            profile.setPersonal(dto.getPersonal());

            return profileRepository.save(profile);

        } catch (IllegalArgumentException e) {
            // User not found
            System.err.println("Error: " + e.getMessage());
            throw e; // rethrow to be handled by controller or global exception handler
        } catch (Exception e) {
            // Catch-all for other unexpected issues
            System.err.println("Unexpected error while adding profile: " + e.getMessage());
            throw new RuntimeException("Failed to add profile. Please try again later.");
        }
    }
}
