package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.MajorReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorReviewRepository extends CrudRepository<MajorReview, Long> {
    // Updated method to match the 'major' property in MajorReview
    List<MajorReview> findByMajorId(Long majorId);

    List<MajorReview> findByRating(Long rating);

    @Query("SELECT AVG(m.rating) FROM MajorReview m WHERE m.major.id = :majorsId")
    Integer findAverageRatingByMajorsId(Long majorsId);

    @Query("SELECT COUNT(m) FROM MajorReview m WHERE m.major.id = :majorsId")
    Long countByMajorsId(Long majorsId);
}
