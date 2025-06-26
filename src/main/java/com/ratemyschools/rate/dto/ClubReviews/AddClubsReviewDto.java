package com.ratemyschools.rate.dto.ClubReviews;

import com.ratemyschools.rate.model.Clubs;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class AddClubsReviewDto {
    public Clubs clubs;
    private Double rating;
    private String review;
    private Date createdAt;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Clubs getClubs() {
        return clubs;
    }

    public void setClubs(Clubs clubs) {
        this.clubs = clubs;
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
