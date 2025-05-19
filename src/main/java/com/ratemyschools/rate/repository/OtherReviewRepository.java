package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.dto.OtherReviews.SchoolOthersAverageDto;
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
    Double findAverageRatingByOthersId(Long othersId);

    @Query("SELECT COUNT(o) FROM OtherReview o WHERE o.other.id = :othersId")
    Long countByOthersId(Long othersId);

    @Query("SELECT new com.ratemyschools.rate.dto.OtherReviews.SchoolOthersAverageDto(o.other.school.id, o.other.school.schoolName, AVG(o.rating)) " +
            "FROM OtherReview o GROUP BY o.other.school.id, o.other.school.schoolName")
    List<SchoolOthersAverageDto> findAverageOthersRatingForAllSchools();

}
