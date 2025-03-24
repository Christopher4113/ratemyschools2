package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.AthleticReviews.GetAthleticsAverageRatingDto;
import com.ratemyschools.rate.dto.AthleticReviews.GetAthleticsReviewDto;
import com.ratemyschools.rate.dto.AthleticReviews.GetAthleticsTotalReviewsDto;
import com.ratemyschools.rate.service.AthleticReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class AthleticsReviewController {
    private final AthleticReviewService athleticReviewService;

    public AthleticsReviewController(AthleticReviewService athleticReviewService) {
        this.athleticReviewService = athleticReviewService;
    }
    @GetMapping("/getAthleticsReview/{id}")
    public List<GetAthleticsReviewDto> getAthleticsReviewInfo(@PathVariable("id") Long id) {
        return athleticReviewService.getAthleticsReviewInfo(id);
    }
    @GetMapping("/getAthleticsAverageRating/{id}")
    public GetAthleticsAverageRatingDto getAthleticsAverageRating(@PathVariable("id") Long id) {
        Integer averageRating = athleticReviewService.getAverageRating(id);
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
}
