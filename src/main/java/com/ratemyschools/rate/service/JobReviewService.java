package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.JobReview.AddJobsReviewDto;
import com.ratemyschools.rate.dto.JobReview.GetJobsReviewDto;
import com.ratemyschools.rate.model.JobsReview;
import com.ratemyschools.rate.repository.JobsReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobReviewService {
    private final JobsReviewRepository jobsReviewRepository;

    public JobReviewService(JobsReviewRepository jobsReviewRepository) {this.jobsReviewRepository = jobsReviewRepository;}

    public List<GetJobsReviewDto> getJobsReviewInfo(Long jobId) {
        List<JobsReview> jobsReviewList = jobsReviewRepository.findByJobsId(jobId);

        return jobsReviewList.stream().map(jobsReview -> {
            GetJobsReviewDto dto = new GetJobsReviewDto();
            dto.setId(jobsReview.getId());
            dto.setRating(jobsReview.getRating());
            dto.setReview(jobsReview.getReview());
            dto.setCreatedAt(jobsReview.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());
    }
    public Integer getAverageRating(Long jobId) {
        return jobsReviewRepository.findAverageRatingByJobsId(jobId);
    }

    public Long getTotalReviews(Long jobId) {
        return jobsReviewRepository.countByJobsId(jobId);
    }
    public JobsReview addReview(AddJobsReviewDto input) {
        JobsReview jobsReview = new JobsReview();
        jobsReview.setJobs(input.getJobs());
        jobsReview.setRating(input.getRating());
        jobsReview.setReview(input.getReview());
        jobsReview.setCreatedAt(input.getCreatedAt());
        return  jobsReviewRepository.save(jobsReview);
    }

    public boolean deleteJobsReviewById(Long id) {
        if (jobsReviewRepository.existsById(id)) {
            jobsReviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
