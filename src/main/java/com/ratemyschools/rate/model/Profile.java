package com.ratemyschools.rate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location" , columnDefinition = "TEXT")
    private String location;

    @Column(name = "major" , columnDefinition = "TEXT")
    private String major;

    @Column(name = "academic_level" , columnDefinition = "TEXT")
    private String academicLevel;

    @Column(name = "campus_setting" , columnDefinition = "TEXT")
    private String campusSetting;

    @Column(name = "finance" , columnDefinition = "TEXT")
    private String finance;

    @Column(name = "goals" , columnDefinition = "TEXT")
    private String goals;

    @Column(name = "living" , columnDefinition = "TEXT")
    private String living;

    @Column(name = "personal" , columnDefinition = "TEXT")
    private String personal;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
