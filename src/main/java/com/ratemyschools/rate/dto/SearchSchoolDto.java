package com.ratemyschools.rate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchSchoolDto {
    private Long id;
    private String schoolName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

}
