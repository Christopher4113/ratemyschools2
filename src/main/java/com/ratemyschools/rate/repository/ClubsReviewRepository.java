package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.ClubsReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubsReviewRepository extends CrudRepository<ClubsReview,Long> {
}
