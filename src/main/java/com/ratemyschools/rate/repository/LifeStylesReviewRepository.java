package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.dto.LifeStylesReview.SchoolLifeStyleAverageDto;
import com.ratemyschools.rate.model.LifeStyleReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LifeStylesReviewRepository extends CrudRepository<LifeStyleReview, Long> {
    List<LifeStyleReview> findByLifeStyle_Id(Long lifeStyleId); // Correct field reference
    List<LifeStyleReview> findByRating(Long rating);

    @Query("SELECT AVG(l.rating) FROM LifeStyleReview l WHERE l.lifeStyle.id = :lifeStyleId")
    Double findAverageRatingByLifeStylesId(Long lifeStyleId);

    @Query("SELECT COUNT(l) FROM LifeStyleReview l WHERE l.lifeStyle.id = :lifeStyleId")
    Long countByLifeStylesId(Long lifeStyleId);

    @Query("SELECT new com.ratemyschools.rate.dto.LifeStylesReview.SchoolLifeStyleAverageDto(l.lifeStyle.school.id, l.lifeStyle.school.schoolName, AVG(l.rating)) " +
            "FROM LifeStyleReview l GROUP BY l.lifeStyle.school.id, l.lifeStyle.school.schoolName")
    List<SchoolLifeStyleAverageDto> findAverageLifeStyleRatingForAllSchools();

}
