package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.MajorReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorReviewRepository extends CrudRepository<MajorReview,Long> {
}
