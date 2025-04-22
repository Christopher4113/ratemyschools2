package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.dto.ClubReviews.SchoolClubsAverageDto;
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

    @Query("SELECT new com.ratemyschools.rate.dto.ClubReviews.SchoolClubsAverageDto(c.clubs.school.id, c.clubs.school.schoolName, AVG(c.rating)) " +
            "FROM ClubsReview c GROUP BY c.clubs.school.id, c.clubs.school.schoolName")
    List<SchoolClubsAverageDto> findAverageClubsRatingForAllSchools();
}
