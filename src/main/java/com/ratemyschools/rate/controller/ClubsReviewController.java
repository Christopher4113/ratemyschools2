package com.ratemyschools.rate.controller;


import com.ratemyschools.rate.dto.ClubReviews.AddClubsReviewDto;
import com.ratemyschools.rate.dto.ClubReviews.GetClubsAverageRatingDto;
import com.ratemyschools.rate.dto.ClubReviews.GetClubsReviewDto;
import com.ratemyschools.rate.dto.ClubReviews.GetClubsTotalReviewsDto;
import com.ratemyschools.rate.model.ClubsReview;
import com.ratemyschools.rate.service.ClubReviewService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class ClubsReviewController {
    private final JwtService jwtService;
    private final ClubReviewService clubReviewService;

    public ClubsReviewController(JwtService jwtService, ClubReviewService clubReviewService) {
        this.jwtService = jwtService;
        this.clubReviewService = clubReviewService;
    }

    @GetMapping("/getClubsReview/{id}")
    public List<GetClubsReviewDto> getClubsReviewInfo(@PathVariable("id") Long id) {
        return clubReviewService.getClubsReviewInfo(id);
    }

    @GetMapping("/getClubsAverageRating/{id}")
    public GetClubsAverageRatingDto getClubsAverageRating(@PathVariable("id") Long id) {
        Integer averageRating = clubReviewService.getAverageRating(id);
        GetClubsAverageRatingDto dto = new GetClubsAverageRatingDto();
        dto.setId(id);
        dto.setAverageRating(averageRating);
        return dto;
    }

    @GetMapping("/getClubsTotalReviews/{id}")
    public GetClubsTotalReviewsDto getClubsTotalReviews(@PathVariable("id") Long id) {
        Long totalReviews = clubReviewService.getTotalReviews(id);
        GetClubsTotalReviewsDto dto = new GetClubsTotalReviewsDto();
        dto.setId(id);
        dto.setTotalReviews(totalReviews);
        return dto;
    }

    @PostMapping("/postClubsReview")
    public ResponseEntity<ClubsReview> addReview(@RequestBody AddClubsReviewDto addClubsReviewDto) {
        try {
            addClubsReviewDto.setCreatedAt(new Date());
            ClubsReview savedReview = clubReviewService.addReview(addClubsReviewDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteClubsReview/{id}")
    public ResponseEntity<String> deleteClubsReview(@PathVariable("id") Long id) {
        boolean deleted = clubReviewService.deleteClubsReviewById(id);
        if (deleted) {
            return ResponseEntity.ok("Reviews deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
