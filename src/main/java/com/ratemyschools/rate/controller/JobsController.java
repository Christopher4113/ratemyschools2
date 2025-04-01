package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.Jobs.GetJobsCategoryDto;
import com.ratemyschools.rate.dto.Jobs.JobsDto;
import com.ratemyschools.rate.service.JobService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class JobsController {
    private final JwtService jwtService;
    private final JobService jobService;

    public JobsController(JwtService jwtService, JobService jobService) {
        this.jwtService = jwtService;
        this.jobService = jobService;
    }

    @GetMapping("/getJobs/{prevId}")
    public List<JobsDto> getJobsInfo(@PathVariable("prevId") Long id) {
        return jobService.getJobsInfo(id);
    }

    @GetMapping("getCategory/{prevId}")
    public GetJobsCategoryDto getCategory(@PathVariable("prevId") Long id) {
        return jobService.getCategory(id);
    }
}
