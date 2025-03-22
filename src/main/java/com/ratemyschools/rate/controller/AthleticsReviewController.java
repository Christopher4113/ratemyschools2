package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.GetAthleticsReviewDto;
import com.ratemyschools.rate.service.AthleticReviewService;
import com.ratemyschools.rate.service.JwtService;
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
}
