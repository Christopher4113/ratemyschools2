package com.ratemyschools.rate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name="schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "school_name")
    private String schoolName;
    @Column(name="description")
    private String description;
    @Column(name="location")
    private String location;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Athletics> athletics;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Clubs> clubs;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Housing> housing;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Jobs> jobs;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LifeStyle> lifestyle;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Major> majors;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Other> other;

}
