package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.IndividualSchoolDto;
import com.ratemyschools.rate.dto.SearchSchoolDto;
import com.ratemyschools.rate.repository.SchoolRepository;
import com.ratemyschools.rate.model.School;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public IndividualSchoolDto getIndividualSchool(Long id) {
        // Retrieve the school from the repository
        Optional<School> school = schoolRepository.findById(id);

        if (school.isPresent()) {
            // Map the School entity to IndividualSchoolDto
            School foundSchool = school.get();
            IndividualSchoolDto schoolDto = new IndividualSchoolDto();
            schoolDto.setId(foundSchool.getId());
            schoolDto.setSchoolName(foundSchool.getSchoolName());
            schoolDto.setDescription(foundSchool.getDescription());
            schoolDto.setLocation(foundSchool.getLocation());
            return schoolDto;
        } else {
            // Throw a NOT_FOUND exception if the school does not exist
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found");
        }
    }
}

