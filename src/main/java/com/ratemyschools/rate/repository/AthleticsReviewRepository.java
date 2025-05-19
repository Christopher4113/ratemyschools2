package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.dto.AthleticReviews.SchoolAthleticsAverageDto;
import com.ratemyschools.rate.model.AthleticsReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleticsReviewRepository extends CrudRepository<AthleticsReview,Long> {
    List<AthleticsReview> findByAthleticsId(Long athleticsId);
    List<AthleticsReview> findByRating(Long rating);

    @Query("SELECT AVG(a.rating) FROM AthleticsReview a WHERE a.athletics.id = :athleticsId")
    Double findAverageRatingByAthleticsId(Long athleticsId);

    @Query("SELECT COUNT(a) FROM AthleticsReview a WHERE a.athletics.id = :athleticsId")
    Long countByAthleticsId(Long athleticsId);

    @Query("SELECT new com.ratemyschools.rate.dto.AthleticReviews.SchoolAthleticsAverageDto(ar.athletics.school.id, ar.athletics.school.schoolName, AVG(ar.rating)) " +
            "FROM AthleticsReview ar GROUP BY ar.athletics.school.id, ar.athletics.school.schoolName")
    List<SchoolAthleticsAverageDto> findAverageAthleticsRatingForAllSchools();

}
