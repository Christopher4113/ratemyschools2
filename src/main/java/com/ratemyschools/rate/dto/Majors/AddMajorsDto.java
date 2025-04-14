package com.ratemyschools.rate.dto.Majors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMajorsDto {
    private Long schoolId;
    private String majorName;
    private String description;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
