package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.AthleticsReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleticsReviewRepository extends CrudRepository<AthleticsReview,Long> {
    List<AthleticsReview> findByAthleticsId(Long athleticsId);
}
