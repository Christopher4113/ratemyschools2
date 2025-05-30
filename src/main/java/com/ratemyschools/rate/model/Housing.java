package com.ratemyschools.rate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "housing")
public class Housing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", columnDefinition = "VARCHAR(255)")
    private String type;

    @Column(name = "description" , columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @OneToMany(mappedBy = "housing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HousingReview> housingReview;
}
