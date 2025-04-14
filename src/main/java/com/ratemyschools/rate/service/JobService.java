package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Jobs.AddJobsDto;
import com.ratemyschools.rate.dto.Jobs.GetJobsCategoryDto;
import com.ratemyschools.rate.dto.Jobs.JobsDto;
import com.ratemyschools.rate.dto.Jobs.UpdateJobsDto;
import com.ratemyschools.rate.model.Jobs;
import com.ratemyschools.rate.model.School;
import com.ratemyschools.rate.repository.JobsRepository;
import com.ratemyschools.rate.repository.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {
    private final JobsRepository jobsRepository;
    private final SchoolRepository schoolRepository;

    public JobService(JobsRepository jobsRepository, SchoolRepository schoolRepository) {
        this.jobsRepository = jobsRepository;
        this.schoolRepository = schoolRepository;
    }

    public List<JobsDto> getJobsInfo(Long schoolId) {
        List<Jobs> jobsList = jobsRepository.findBySchoolId(schoolId);

        return jobsList.stream().map(jobs -> {
            JobsDto dto = new JobsDto();
            dto.setId(jobs.getId());
            dto.setCategory(jobs.getCategory());
            dto.setDescription(jobs.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public GetJobsCategoryDto getCategory(Long id) {
        Optional<Jobs> jobsOptional = jobsRepository.findById(id);

        return jobsOptional.map(jobs -> {
            GetJobsCategoryDto dto = new GetJobsCategoryDto();
            dto.setId(jobs.getId());
            dto.setCategory(jobs.getCategory());
            return dto;
        }).orElseGet(() -> {
            GetJobsCategoryDto dto = new GetJobsCategoryDto();
            dto.setId(id);
            dto.setCategory("Category not found");
            return dto;
        });
    }
    public Jobs addJobs(AddJobsDto input) {
        School school = schoolRepository.findById(input.getSchoolId())
                .orElseThrow(() -> new IllegalArgumentException("School not found with id: " + input.getSchoolId()));
        Jobs jobs = new Jobs();
        jobs.setSchool(school);
        jobs.setCategory(input.getCategory());
        jobs.setDescription(input.getDescription());
        return  jobsRepository.save(jobs);
    }
    public Jobs updateJobs(UpdateJobsDto input) {
        Optional<Jobs> optionalJobs = jobsRepository.findById(input.getId());

        if (optionalJobs.isPresent()) {
            Jobs jobs = optionalJobs.get();
            jobs.setCategory(input.getCategory());
            jobs.setDescription(input.getDescription());
            return jobsRepository.save(jobs);
        } else {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Jobs not found.");
        }
    }
    public boolean deleteJobsById(Long id) {
        if (jobsRepository.existsById(id)) {
            jobsRepository.deleteById(id);
            return true;
        }
        return false;
    }



}