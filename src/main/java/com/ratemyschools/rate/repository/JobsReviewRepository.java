package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.dto.JobReview.SchoolJobsAverageDto;
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
    Double findAverageRatingByJobsId(Long jobsId);

    @Query("SELECT COUNT(j) FROM JobsReview j WHERE j.jobs.id = :jobsId")
    Long countByJobsId(Long jobsId);

    @Query("SELECT new com.ratemyschools.rate.dto.JobReview.SchoolJobsAverageDto(j.jobs.school.id, j.jobs.school.schoolName, AVG(j.rating)) " +
            "FROM JobsReview j GROUP BY j.jobs.school.id, j.jobs.school.schoolName")
    List<SchoolJobsAverageDto> findAverageJobsRatingForAllSchools();

}
