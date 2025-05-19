package com.ratemyschools.rate.dto.AthleticReviews;

import com.ratemyschools.rate.model.Athletics;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddAthleticsReviewDto {
    private Athletics athletics;
    private Double rating;
    private String review;
    private Date createdAt;

    public Athletics getAthletics() {
        return athletics;
    }

    public void setAthletics(Athletics athletics) {
        this.athletics = athletics;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
