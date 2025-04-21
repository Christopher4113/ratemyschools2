package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.IndividualSchoolDto;
import com.ratemyschools.rate.dto.School.AddSchoolDto;
import com.ratemyschools.rate.dto.School.UpdateSchoolDto;
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
            schoolDto.setLocation(school.getLocation());
            schoolDto.setDescription(school.getDescription());
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

    public School addSchool(AddSchoolDto input) {
        School school = new School();
        school.setSchoolName(input.getSchoolName());
        school.setDescription(input.getDescription());
        school.setLocation(input.getLocation());

        return schoolRepository.save(school);
    }

    public School updateSchool(UpdateSchoolDto updateSchoolDto) {
        Optional<School> optionalSchool = schoolRepository.findById(updateSchoolDto.getId());

        if (optionalSchool.isPresent()) {
            School school = optionalSchool.get();
            school.setSchoolName(updateSchoolDto.getSchoolName());
            school.setDescription(updateSchoolDto.getDescription());
            school.setLocation(updateSchoolDto.getLocation());
            return schoolRepository.save(school);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found");
        }
    }
    public boolean deleteSchoolById(Long id) {
        if (schoolRepository.existsById(id)) {
            schoolRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

