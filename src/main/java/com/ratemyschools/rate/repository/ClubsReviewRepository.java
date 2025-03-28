package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.ClubsReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubsReviewRepository extends CrudRepository<ClubsReview,Long> {
    List<ClubsReview> findByClubsId(Long clubsId);
    List<ClubsReview> findByRating(Long rating);

    @Query("SELECT AVG(c.rating) FROM ClubsReview c WHERE c.clubs.id = :clubsId")
    Integer findAverageRatingByClubsId(Long clubsId);

    @Query("SELECT COUNT(c) FROM ClubsReview c WHERE c.clubs.id = :clubsId")
    Long countByClubsId(Long clubsId);
}
