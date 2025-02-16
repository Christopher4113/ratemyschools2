package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.LifeStyleReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LifeStyleReviewRepository extends CrudRepository<LifeStyleReview,Long> {
}
