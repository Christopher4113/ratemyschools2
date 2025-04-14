package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.Jobs.AddJobsDto;
import com.ratemyschools.rate.dto.Jobs.GetJobsCategoryDto;
import com.ratemyschools.rate.dto.Jobs.JobsDto;
import com.ratemyschools.rate.dto.Jobs.UpdateJobsDto;
import com.ratemyschools.rate.model.Jobs;
import com.ratemyschools.rate.service.JobService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("getJobsCategory/{prevId}")
    public GetJobsCategoryDto getCategory(@PathVariable("prevId") Long id) {
        return jobService.getCategory(id);
    }

    @PostMapping("/postJobs")
    public ResponseEntity<Jobs> addJobs(@RequestBody AddJobsDto addJobsDto) {
        try {
            Jobs jobs = jobService.addJobs(addJobsDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(jobs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateJobs")
    public ResponseEntity<Jobs> updateJobs(@RequestBody UpdateJobsDto updateJobsDto) {
        try {
            Jobs updatedJobs = jobService.updateJobs(updateJobsDto);
            return ResponseEntity.ok(updatedJobs);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteJobs/{id}")
    public ResponseEntity<String> deleteJobs(@PathVariable("id") Long id) {
        boolean deleted = jobService.deleteJobsById(id);
        if (deleted) {
            return ResponseEntity.ok("Jobs deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
