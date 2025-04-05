package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.OtherReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherReviewRepository extends CrudRepository<OtherReview,Long> {
    List<OtherReview> findByOtherId(Long otherId);
    List<OtherReview> findByRating(Long rating);

    @Query("SELECT AVG(o.rating) FROM OtherReview o WHERE o.other.id = :othersId")
    Integer findAverageRatingByOthersId(Long othersId);

    @Query("SELECT COUNT(o) FROM OtherReview o WHERE o.other.id = :othersId")
    Long countByOthersId(Long othersId);
}
