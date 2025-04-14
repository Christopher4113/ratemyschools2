package com.ratemyschools.rate.dto.LifeStyles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddLifeStylesDto {
    private Long schoolId;
    private String category;
    private String description;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
