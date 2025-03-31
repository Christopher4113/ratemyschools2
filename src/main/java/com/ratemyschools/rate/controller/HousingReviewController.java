package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.HousingReviews.AddHousingReviewDto;
import com.ratemyschools.rate.dto.HousingReviews.GetHousingAverageRatingDto;
import com.ratemyschools.rate.dto.HousingReviews.GetHousingReviewDto;
import com.ratemyschools.rate.dto.HousingReviews.GetHousingTotalReviewsDto;
import com.ratemyschools.rate.model.HousingReview;
import com.ratemyschools.rate.service.HousingReviewService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class HousingReviewController {
    private final JwtService jwtService;
    private final HousingReviewService housingReviewService;

    public HousingReviewController(JwtService jwtService, HousingReviewService housingReviewService) {
        this.jwtService = jwtService;
        this.housingReviewService = housingReviewService;
    }

    @GetMapping("/getHousingReview/{id}")
    public List<GetHousingReviewDto> getHousingReviewInfo(@PathVariable("id") Long id) {
        return housingReviewService.getHousingReviewInfo(id);
    }

    @GetMapping("/getHousingAverageRating/{id}")
    public GetHousingAverageRatingDto getHousingAverageRating(@PathVariable("id") Long id) {
        Integer averageRating = housingReviewService.getAverageRating(id);
        GetHousingAverageRatingDto dto = new GetHousingAverageRatingDto();
        dto.setId(id);
        dto.setAverageRating(averageRating);
        return dto;
    }

    @GetMapping("/getHousingTotalReviews/{id}")
    public GetHousingTotalReviewsDto getHousingTotalReviews(@PathVariable("id") Long id) {
        Long totalReviews = housingReviewService.getTotalReviews(id);
        GetHousingTotalReviewsDto dto = new GetHousingTotalReviewsDto();
        dto.setId(id);
        dto.setTotalReviews(totalReviews);
        return dto;
    }

    @PostMapping("/postHousingReview")
    public ResponseEntity<HousingReview> addReview(@RequestBody AddHousingReviewDto addHousingReviewDto) {
        try {
            addHousingReviewDto.setCreatedAt(new Date());
            HousingReview savedReview = housingReviewService.addReview(addHousingReviewDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
