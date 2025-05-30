package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.JobReview.*;
import com.ratemyschools.rate.model.JobsReview;
import com.ratemyschools.rate.service.JobReviewService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/auth")
@RestController
public class JobsReviewController {
    private final JwtService jwtService;
    private final JobReviewService jobReviewService;

    public JobsReviewController(JwtService jwtService, JobReviewService jobReviewService) {
        this.jwtService = jwtService;
        this.jobReviewService = jobReviewService;
    }

    @GetMapping("/getJobsReview/{id}")
    public List<GetJobsReviewDto> getJobsReviewInfo(@PathVariable("id") Long id) {
        return jobReviewService.getJobsReviewInfo(id);
    }

    @GetMapping("/getJobsAverageRating/{id}")
    public GetJobsAverageRatingDto getJobsAverageRating(@PathVariable("id") Long id) {
        Double averageRating = jobReviewService.getAverageRating(id);
        GetJobsAverageRatingDto dto = new GetJobsAverageRatingDto();
        dto.setId(id);
        dto.setAverageRating(averageRating);
        return dto;
    }

    @GetMapping("/getJobsTotalReviews/{id}")
    public GetJobsTotalReviewsDto getJobsTotalReviews(@PathVariable("id") Long id) {
        Long totalReviews = jobReviewService.getTotalReviews(id);
        GetJobsTotalReviewsDto dto = new GetJobsTotalReviewsDto();
        dto.setId(id);
        dto.setTotalReviews(totalReviews);
        return dto;
    }
    @PostMapping("/postJobsReview")
    public ResponseEntity<JobsReview> addReview(@RequestBody AddJobsReviewDto addJobsReviewDto) {
        try {
            addJobsReviewDto.setCreatedAt(new Date());
            JobsReview savedReview = jobReviewService.addReview(addJobsReviewDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteJobsReview/{id}")
    public ResponseEntity<String> deleteJobsReview(@PathVariable("id") Long id) {
        boolean deleted = jobReviewService.deleteJobsReviewById(id);
        if (deleted) {
            return ResponseEntity.ok("Reviews deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllSchoolsJobsAverage")
    public ResponseEntity<List<SchoolJobsAverageDto>> getAllSchoolsJobsAverage() {
        List<SchoolJobsAverageDto> averages = jobReviewService.getAverageRatingsForAllSchools();
        return ResponseEntity.ok(averages);
    }

}
