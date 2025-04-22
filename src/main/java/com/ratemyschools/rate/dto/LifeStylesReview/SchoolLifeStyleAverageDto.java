package com.ratemyschools.rate.dto.LifeStylesReview;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolLifeStyleAverageDto {
    private Long schoolId;
    private String schoolName;
    private Double averageRating;

    public SchoolLifeStyleAverageDto(Long schoolId, String schoolName, Double averageRating) {
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
