package com.ratemyschools.rate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "clubs")
public class Clubs  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(name = "club_name", columnDefinition = "VARCHAR(255)")
    private String clubName;

    @Column(name = "description" , columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "clubs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubsReview> clubsReview;
}
