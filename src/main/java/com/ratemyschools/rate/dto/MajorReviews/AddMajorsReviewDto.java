package com.ratemyschools.rate.dto.MajorReviews;

import com.ratemyschools.rate.model.Major;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddMajorsReviewDto {
    private Major major;
    private Integer rating;
    private String review;
    private Date createdAt;

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
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
