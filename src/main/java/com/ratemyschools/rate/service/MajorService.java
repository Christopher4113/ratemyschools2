package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Majors.AddMajorsDto;
import com.ratemyschools.rate.dto.Majors.GetMajorsCategoryDto;
import com.ratemyschools.rate.dto.Majors.MajorsDto;
import com.ratemyschools.rate.dto.Majors.UpdateMajorsDto;
import com.ratemyschools.rate.model.Major;
import com.ratemyschools.rate.model.School;
import com.ratemyschools.rate.repository.MajorRepository;
import com.ratemyschools.rate.repository.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MajorService {
    private final MajorRepository majorRepository;
    private final SchoolRepository schoolRepository;

    public MajorService(MajorRepository majorRepository, SchoolRepository schoolRepository) {
        this.majorRepository = majorRepository;
        this.schoolRepository = schoolRepository;
    }

    public List<MajorsDto> getMajorsInfo(Long schoolId) {
        List<Major> majorsList = majorRepository.findBySchoolId(schoolId);

        return majorsList.stream().map(majors -> {
            MajorsDto dto = new MajorsDto();
            dto.setId(majors.getId());
            dto.setMajorName(majors.getMajorName());
            dto.setDescription(majors.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public GetMajorsCategoryDto getMajorName(Long id) {
        Optional<Major> majorsOptional = majorRepository.findById(id);

        return majorsOptional.map(majors -> {
            GetMajorsCategoryDto dto = new GetMajorsCategoryDto();
            dto.setId(majors.getId());
            dto.setMajorName(majors.getMajorName());
            return dto;
        }).orElseGet(() -> {
           GetMajorsCategoryDto dto = new GetMajorsCategoryDto();
           dto.setId(id);
           dto.setMajorName("Major Names not found");
           return dto;
        });
    }

    public Major addMajors(AddMajorsDto input) {
        School school = schoolRepository.findById(input.getSchoolId())
                .orElseThrow(() -> new IllegalArgumentException("School not found with id: " + input.getSchoolId()));

        Major major = new Major();
        major.setSchool(school);
        major.setMajorName(input.getMajorName());
        major.setDescription(input.getDescription());

        return majorRepository.save(major);
    }
    public Major updateMajors(UpdateMajorsDto input) {
        Optional<Major> optionalMajor = majorRepository.findById(input.getId());

        if (optionalMajor.isPresent()) {
            Major major = optionalMajor.get();
            major.setMajorName(input.getMajorName());
            major.setDescription(input.getDescription());
            return majorRepository.save(major);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }
    public boolean deleteMajorsById(Long id) {
        if (majorRepository.existsById(id)) {
            majorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
