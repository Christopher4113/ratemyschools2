package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Jobs.GetJobsCategoryDto;
import com.ratemyschools.rate.dto.Jobs.JobsDto;
import com.ratemyschools.rate.model.Jobs;
import com.ratemyschools.rate.repository.JobsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {
    private final JobsRepository jobsRepository;

    public JobService(JobsRepository jobsRepository) {this.jobsRepository = jobsRepository;}

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
}
