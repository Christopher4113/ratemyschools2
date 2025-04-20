package com.ratemyschools.rate.dto.Profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProfileDto {
    private Long id;
    private String location;
    private String major;
    private String academicLevel;
    private String campusSetting;
    private String finance;
    private String goals;
    private String living;
    private String personal;
}
