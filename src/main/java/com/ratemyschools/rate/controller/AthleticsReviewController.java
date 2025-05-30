package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.AthleticReviews.*;
import com.ratemyschools.rate.model.AthleticsReview;
import com.ratemyschools.rate.service.AthleticReviewService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

@RequestMapping("/auth")
@RestController
public class AthleticsReviewController {
    private final JwtService jwtService;
    private final AthleticReviewService athleticReviewService;

    public AthleticsReviewController(JwtService jwtService, AthleticReviewService athleticReviewService) {
        this.jwtService = jwtService;
        this.athleticReviewService = athleticReviewService;
    }
    @GetMapping("/getAthleticsReview/{id}")
    public List<GetAthleticsReviewDto> getAthleticsReviewInfo(@PathVariable("id") Long id) {
        return athleticReviewService.getAthleticsReviewInfo(id);
    }
    @GetMapping("/getAthleticsAverageRating/{id}")
    public GetAthleticsAverageRatingDto getAthleticsAverageRating(@PathVariable("id") Long id) {
        Double averageRating = athleticReviewService.getAverageRating(id);
        GetAthleticsAverageRatingDto dto = new GetAthleticsAverageRatingDto();
        dto.setId(id);
        dto.setAverageRating(averageRating);
        return dto;
    }
    @GetMapping("/getAthleticsTotalReviews/{id}")
    public GetAthleticsTotalReviewsDto getAthleticsTotalReviews(@PathVariable("id") Long id) {
        Long totalReviews = athleticReviewService.getTotalReviews(id);
        GetAthleticsTotalReviewsDto dto = new GetAthleticsTotalReviewsDto();
        dto.setId(id);
        dto.setTotalReviews(totalReviews);
        return dto;
    }

    @PostMapping("/postAthleticsReview")
    public ResponseEntity<AthleticsReview> addReview(@RequestBody AddAthleticsReviewDto addAthleticsReviewDto) {
        try {
            // Call the service method to save the review
            addAthleticsReviewDto.setCreatedAt(new Date());
            AthleticsReview savedReview = athleticReviewService.addReview(addAthleticsReviewDto);

            // Return the saved review with a CREATED status
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteAthleticsReview/{id}")
    public ResponseEntity<String> deleteAthleticsReview(@PathVariable("id") Long id) {
        boolean deleted = athleticReviewService.deleteAthleticReviewsById(id);
        if (deleted) {
            return ResponseEntity.ok("Reviews deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllSchoolsAthleticsAverage")
    public ResponseEntity<List<SchoolAthleticsAverageDto>> getAllSchoolsAthleticsAverage() {
        List<SchoolAthleticsAverageDto> averages = athleticReviewService.getAverageRatingsForAllSchools();
        return ResponseEntity.ok(averages);
    }
}
