package com.ratemyschools.rate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "athletics_review")
public class AthleticsReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rating", columnDefinition = "DOUBLE PRECISION")
    private Double rating;

    @Column(name = "review", columnDefinition = "TEXT")
    private String review;

    @Column(name="username", columnDefinition = "TEXT")
    private String username;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "athletics_id", nullable = false)
    private Athletics athletics;
}
