package com.ratemyschools.rate.controller;
import com.ratemyschools.rate.dto.LifeStylesReview.*;
import com.ratemyschools.rate.model.LifeStyleReview;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.LifeStyleReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class LifeStylesReviewController {
    private final JwtService jwtService;
    private final LifeStyleReviewService lifeStyleReviewService;

    public LifeStylesReviewController(JwtService jwtService, LifeStyleReviewService lifeStyleReviewService) {
        this.jwtService = jwtService;
        this.lifeStyleReviewService = lifeStyleReviewService;
    }

    @GetMapping("/getLifeStylesReview/{id}")
    public List<GetLifeStylesReviewDto> getLifeStylesReviewInfo(@PathVariable("id") Long id) {
        return  lifeStyleReviewService.getLifeStylesReviewInfo(id);
    }

    @GetMapping("/getLifeStylesAverageRating/{id}")
    public GetLifeStylesAverageRatingDto getLifeStylesAverageRating(@PathVariable("id") Long id) {
        Integer averageRating = lifeStyleReviewService.getAverageRating(id);
        GetLifeStylesAverageRatingDto dto = new GetLifeStylesAverageRatingDto();
        dto.setId(id);
        dto.setAverageRating(averageRating);
        return dto;
    }

    @GetMapping("/getLifeStylesTotalReviews/{id}")
    public GetLifeStylesTotalReviewDto getLifeStylesTotalReviews(@PathVariable("id") Long id) {
        Long totalReviews = lifeStyleReviewService.getTotalReviews(id);
        GetLifeStylesTotalReviewDto dto = new GetLifeStylesTotalReviewDto();
        dto.setId(id);
        dto.setTotalReviews(totalReviews);
        return dto;
    }

    @PostMapping("/postLifeStylesReview")
    public ResponseEntity<LifeStyleReview> addReview(@RequestBody AddLifeStylesReviewDto addLifeStylesReviewDto) {
        try {
            addLifeStylesReviewDto.setCreatedAt(new Date());
            LifeStyleReview savedReview = lifeStyleReviewService.addReview(addLifeStylesReviewDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/deleteLifeStylesReview/{id}")
    public ResponseEntity<String> deleteLifeStylesReview(@PathVariable("id") Long id) {
        boolean deleted = lifeStyleReviewService.deleteLifeStyleReviewById(id);
        if (deleted) {
            return ResponseEntity.ok("Reviews deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllSchoolsLifeStyleAverage")
    public ResponseEntity<List<SchoolLifeStyleAverageDto>> getAllSchoolsLifeStyleAverage() {
        List<SchoolLifeStyleAverageDto> averages = lifeStyleReviewService.getAverageRatingsForAllSchools();
        return ResponseEntity.ok(averages);
    }

}
