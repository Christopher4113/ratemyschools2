package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.SearchSchoolDto;
import com.ratemyschools.rate.repository.SchoolRepository;
import com.ratemyschools.rate.model.School;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<SearchSchoolDto> getAllSchoolNames() {
        Iterable<School> schools = schoolRepository.findAll();
        List<SearchSchoolDto> schoolDtos = new ArrayList<>();
        for (School school : schools) {
            SearchSchoolDto schoolDto = new SearchSchoolDto();
            schoolDto.setId(school.getId());
            schoolDto.setSchoolName(school.getSchoolName());
            schoolDtos.add(schoolDto);
        }
        return schoolDtos;
    }
}
