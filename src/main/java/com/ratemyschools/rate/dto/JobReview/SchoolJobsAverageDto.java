package com.ratemyschools.rate.dto.JobReview;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolJobsAverageDto {
    private Long schoolId;
    private String schoolName;
    private Double averageRating;

    public SchoolJobsAverageDto(Long schoolId, String schoolName, Double averageRating) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.averageRating = averageRating;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public Double getAverageRating() {
        return averageRating;
    }
}
