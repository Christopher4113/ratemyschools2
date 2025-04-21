package com.ratemyschools.rate.dto;

import com.ratemyschools.rate.dto.Profile.GetProfileDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReccomendationRequestDto {
    private GetProfileDto profile;
    private List<SearchSchoolDto> schools;
}
