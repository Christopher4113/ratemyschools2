package com.ratemyschools.rate.dto.LifeStylesReview;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetLifeStylesAverageRatingDto {
    private Long id;
    private Double averageRating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
