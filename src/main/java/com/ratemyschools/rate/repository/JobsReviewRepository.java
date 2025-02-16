package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.JobsReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsReviewRepository extends CrudRepository<JobsReview,Long> {
}
