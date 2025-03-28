package com.ratemyschools.rate.dto.ClubReviews;

import com.ratemyschools.rate.model.Clubs;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class AddClubsReviewDto {
    public Clubs clubs;
    private Integer rating;
    private String review;
    private Date createdAt;

    public Clubs getClubs() {
        return clubs;
    }

    public void setClubs(Clubs clubs) {
        this.clubs = clubs;
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
