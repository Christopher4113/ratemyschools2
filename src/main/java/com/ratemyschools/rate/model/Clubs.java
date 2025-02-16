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

    @Column(name = "club_name")
    private String clubName;

    @OneToMany(mappedBy = "clubs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubsReview> clubsReview;
}
