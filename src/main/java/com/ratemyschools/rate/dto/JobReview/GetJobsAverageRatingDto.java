package com.ratemyschools.rate.dto.JobReview;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetJobsAverageRatingDto {
    private Long id;
    private Integer averageRating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Integer averageRating) {
        this.averageRating = averageRating;
    }
}
