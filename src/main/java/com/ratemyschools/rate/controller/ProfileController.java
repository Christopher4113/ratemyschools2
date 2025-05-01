package com.ratemyschools.rate.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratemyschools.rate.dto.Profile.AddProfileDto;
import com.ratemyschools.rate.dto.Profile.GetProfileDto;
import com.ratemyschools.rate.dto.ReccomendationRequestDto;
import com.ratemyschools.rate.model.Profile;
import com.ratemyschools.rate.repository.UserRepository;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.ProfileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;



@RequestMapping("/auth")
@RestController
public class ProfileController {

    @Value("${groq.api.key}")
    private String groqApiKey;
    private final ObjectMapper objectMapper = new ObjectMapper();

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
    @PostMapping("/recommendSchool")
    public ResponseEntity<String> recommendSchool(@RequestBody ReccomendationRequestDto dto) {
        try {
            GetProfileDto profile = dto.getProfile();
            var schools = dto.getSchools();

            String prompt = "Given the student's profile:\n" +
                    "Location: " + profile.getLocation() + "\n" +
                    "Major: " + profile.getMajor() + "\n" +
                    "Academic Level: " + profile.getAcademicLevel() + "\n" +
                    "Campus Setting: " + profile.getCampusSetting() + "\n" +
                    "Financial Considerations: " + profile.getFinance() + "\n" +
                    "Goals: " + profile.getGoals() + "\n" +
                    "Living Preferences: " + profile.getLiving() + "\n" +
                    "Personal Notes: " + profile.getPersonal() + "\n\n" +
                    "And the list of schools:\n" +
                    schools.stream()
                            .map(s -> "- " + s.getSchoolName() + ": " + s.getDescription() + " (" + s.getLocation() + ")")
                            .collect(Collectors.joining("\n")) +
                    "\n\nOut of these ONLY, which one is the BEST match for this student? Return only the name.";

            // Create request body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "meta-llama/llama-4-scout-17b-16e-instruct");
            requestBody.put("temperature", 1);
            requestBody.put("top_p", 1);
            requestBody.put("stream", false);
            requestBody.put("max_completion_tokens", 8192);
            requestBody.put("messages", List.of(Map.of("role", "user", "content", prompt)));

            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(groqApiKey);

            // Wrap headers and body
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            // Make the request
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(
                    "https://api.groq.com/openai/v1/chat/completions",
                    requestEntity,
                    String.class
            );

            // Parse the response
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                JsonNode parsed = objectMapper.readTree(response.getBody());
                String content = parsed.path("choices").get(0).path("message").path("content").asText();
                return ResponseEntity.ok(content.trim());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get a recommendation.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
        }
    }

}