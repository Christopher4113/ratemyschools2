package com.ratemyschools.rate.dto.MajorReviews;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolMajorsAverageDto {
    private Long schoolId;
    private String schoolName;
    private Double averageRating;

    public SchoolMajorsAverageDto(Long schoolId, String schoolName, Double averageRating) {
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
