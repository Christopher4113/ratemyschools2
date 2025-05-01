package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.MajorReviews.*;
import com.ratemyschools.rate.model.MajorReview;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.MajorReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/auth")
@RestController
public class MajorsReviewController {
    private final JwtService jwtService;
    private final MajorReviewService majorReviewService;

    public MajorsReviewController(JwtService jwtService, MajorReviewService majorReviewService) {
        this.jwtService = jwtService;
        this.majorReviewService = majorReviewService;
    }

    @GetMapping("/getMajorsReview/{id}")
    public List<GetMajorsReviewDto> getMajorsReviewInfo(@PathVariable("id") Long id) {
        return majorReviewService.getMajorsReviewInfo(id);
    }

    @GetMapping("/getMajorsAverageRating/{id}")
    public GetMajorsAverageRatingDto getMajorsAverageRating(@PathVariable("id") Long id) {
        Integer averageRating = majorReviewService.getAverageRating(id);
        GetMajorsAverageRatingDto dto = new GetMajorsAverageRatingDto();
        dto.setId(id);
        dto.setAverageRating(averageRating);
        return dto;
    }

    @GetMapping("/getMajorsTotalReviews/{id}")
    public GetMajorsTotalReviewsDto getMajorsTotalReviews(@PathVariable("id") Long id) {
        Long totalReviews = majorReviewService.getTotalReviews(id);
        GetMajorsTotalReviewsDto dto = new GetMajorsTotalReviewsDto();
        dto.setId(id);
        dto.setTotalReviews(totalReviews);
        return dto;
    }

    @PostMapping("/postMajorsReview")
    public ResponseEntity<MajorReview> addReview(@RequestBody AddMajorsReviewDto addMajorsReviewDto) {
        try {
            addMajorsReviewDto.setCreatedAt(new Date());
            MajorReview savedReview = majorReviewService.addReview(addMajorsReviewDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteMajorsReview/{id}")
    public ResponseEntity<String> deleteMajorsReviews(@PathVariable("id") Long id) {
        boolean deleted = majorReviewService.deleteMajorReviewById(id);
        if (deleted) {
            return ResponseEntity.ok("Reviews deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllSchoolsMajorsAverage")
    public ResponseEntity<List<SchoolMajorsAverageDto>> getAllSchoolsMajorsAverage() {
        List<SchoolMajorsAverageDto> averages = majorReviewService.getAverageRatingsForAllSchools();
        return ResponseEntity.ok(averages);
    }

}
