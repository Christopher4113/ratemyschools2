package com.ratemyschools.rate.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "athletics")
public class Athletics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(name = "category", columnDefinition = "VARCHAR(255)")
    private String category;

    @OneToMany(mappedBy = "athletics", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AthleticsReview> athleticsReview;
}
