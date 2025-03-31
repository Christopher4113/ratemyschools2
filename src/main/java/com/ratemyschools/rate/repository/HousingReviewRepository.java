package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.HousingReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingReviewRepository extends CrudRepository<HousingReview,Long> {
    List<HousingReview> findByHousingId(Long housingId);
    List<HousingReview> findByRating(Long rating);

    @Query("SELECT AVG(h.rating) FROM HousingReview h WHERE h.housing.id =:housingId")
    Integer findAverageRatingByHousingId(Long housingId);

    @Query("SELECT COUNT(h) FROM HousingReview h WHERE h.housing.id =:housingId")
    Long countByHousingId(Long housingId);
}
