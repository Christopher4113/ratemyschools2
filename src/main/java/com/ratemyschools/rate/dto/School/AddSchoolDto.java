package com.ratemyschools.rate.dto.School;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddSchoolDto {
    private String schoolName;
    private String description;
    private String location;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
