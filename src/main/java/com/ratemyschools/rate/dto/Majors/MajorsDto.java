package com.ratemyschools.rate.dto.Majors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MajorsDto {
    private Long id;
    private String majorName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
