package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.JobsReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsReviewRepository extends CrudRepository<JobsReview,Long> {
    List<JobsReview> findByJobsId(Long jobsId);
    List<JobsReview> findByRating(Long rating);

    @Query("SELECT AVG(j.rating) FROM JobsReview j WHERE j.jobs.id = :jobsId")
    Integer findAverageRatingByJobsId(Long jobsId);

    @Query("SELECT COUNT(j) FROM JobsReview j WHERE j.jobs.id = :jobsId")
    Long countByJobsId(Long jobsId);
}
