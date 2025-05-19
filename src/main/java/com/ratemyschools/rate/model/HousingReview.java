package com.ratemyschools.rate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "housing_reviews")
public class HousingReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rating", columnDefinition = "DOUBLE PRECISION")
    private Double rating;

    @Column(name = "review", columnDefinition = "TEXT")
    private String review;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "housing_id", nullable = false)
    private Housing housing;
}
