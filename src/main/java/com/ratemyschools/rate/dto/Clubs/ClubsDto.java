package com.ratemyschools.rate.dto.Clubs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubsDto {
    private Long id;
    private String clubName;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
