package com.ratemyschools.rate.dto.ClubReviews;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetClubsTotalReviewsDto {
    private Long id;
    private Long totalReviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(Long totalReviews) {
        this.totalReviews = totalReviews;
    }
}
