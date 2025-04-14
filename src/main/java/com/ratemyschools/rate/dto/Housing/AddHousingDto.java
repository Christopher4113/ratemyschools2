package com.ratemyschools.rate.dto.Housing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddHousingDto {
    private Long schoolId;
    private String type;
    private String description;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
