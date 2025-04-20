package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.Profile.AddProfileDto;
import com.ratemyschools.rate.dto.Profile.GetProfileDto;
import com.ratemyschools.rate.model.Profile;
import com.ratemyschools.rate.repository.UserRepository;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class ProfileController {

    private final JwtService jwtService;
    private final ProfileService profileService;
    private final UserRepository userRepository;


    public ProfileController(JwtService jwtService, ProfileService profileService, UserRepository userRepository) {
        this.profileService = profileService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }


    @PostMapping("/postProfile")
    public ResponseEntity<Profile> addProfile(@RequestBody AddProfileDto addProfileDto) {
        try {
            Profile profile = profileService.addProfile(addProfileDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getProfile/{userId}")
    public Optional<GetProfileDto> getProfile(@PathVariable Long userId) {
        return profileService.getProfileInfo(userId);
    }
}