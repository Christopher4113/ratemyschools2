package com.ratemyschools.rate.dto.OtherReviews;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOthersAverageRatingDto {
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
