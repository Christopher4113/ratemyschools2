package com.ratemyschools.rate.dto.OtherReviews;

import com.ratemyschools.rate.model.Other;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddOthersReviewDto {
    private Other other;
    private Integer rating;
    private String review;
    private Date createdAt;

    public Other getOther() {
        return other;
    }

    public void setOther(Other other) {
        this.other = other;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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
