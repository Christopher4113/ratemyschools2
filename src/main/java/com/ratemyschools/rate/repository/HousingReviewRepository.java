package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.HousingReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingReviewRepository extends CrudRepository<HousingReview,Long> {
}
