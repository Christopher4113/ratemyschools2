package com.ratemyschools.rate.service;

import com.ratemyschools.rate.repository.SchoolRepository;
import com.ratemyschools.rate.model.School;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<String> getAllSchoolNames() {
        Iterable<School> schools = schoolRepository.findAll();
        List<String> schoolNames = new ArrayList<>();
        for (School school : schools) {
            schoolNames.add(school.getSchoolName());
        }
        return schoolNames;
    }
}
