package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.OtherReviews.*;
import com.ratemyschools.rate.model.OtherReview;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.OtherReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/auth")
@RestController
public class OthersReviewController {
    private final JwtService jwtService;
    private final OtherReviewService otherReviewService;

    public OthersReviewController(JwtService jwtService, OtherReviewService otherReviewService) {
        this.jwtService = jwtService;
        this.otherReviewService = otherReviewService;
    }

    @GetMapping("/getOthersReview/{id}")
    public List<GetOthersReviewDto> getOthersReviewInfo(@PathVariable("id") Long id) {
        return otherReviewService.getOthersReviewInfo(id);
    }

    @GetMapping("/getOthersAverageRating/{id}")
    public GetOthersAverageRatingDto getOthersAverageRating(@PathVariable("id") Long id) {
        Double averageRating = otherReviewService.getAverageRating(id);
        GetOthersAverageRatingDto dto = new GetOthersAverageRatingDto();
        dto.setId(id);
        dto.setAverageRating(averageRating);
        return dto;
    }

    @GetMapping("/getOthersTotalReviews/{id}")
    public GetOthersTotalReviewsDto getOthersTotalReviews(@PathVariable("id") Long id) {
        Long totalReviews = otherReviewService.getTotalReviews(id);
        GetOthersTotalReviewsDto dto = new GetOthersTotalReviewsDto();
        dto.setId(id);
        dto.setTotalReviews(totalReviews);
        return dto;
    }

    @PostMapping("/postOthersReview")
    public ResponseEntity<OtherReview> addReview(@RequestBody AddOthersReviewDto addOthersReviewDto) {
        try {
            addOthersReviewDto.setCreatedAt(new Date());
            OtherReview savedReview = otherReviewService.addReview(addOthersReviewDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteOthersReview/{id}")
    public ResponseEntity<String> deleteOthersReview(@PathVariable("id") Long id) {
        boolean deleted = otherReviewService.deleteOtherReviewById(id);
        if (deleted) {
            return ResponseEntity.ok("Reviews deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllSchoolsOthersAverage")
    public ResponseEntity<List<SchoolOthersAverageDto>> getAllSchoolsOthersAverage() {
        List<SchoolOthersAverageDto> averages = otherReviewService.getAverageRatingsForAllSchools();
        return ResponseEntity.ok(averages);
    }

}
