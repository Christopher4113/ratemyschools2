package com.ratemyschools.rate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "majors")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "major_name")
    private String majorName;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MajorReview> majorReview;
}
