package com.ratemyschools.rate.dto.Clubs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddClubsDto {
    private Long schoolId;
    private String clubName;
    private String description;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
