package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Others.AddOthersDto;
import com.ratemyschools.rate.dto.Others.GetOthersCategoryDto;
import com.ratemyschools.rate.dto.Others.OthersDto;
import com.ratemyschools.rate.dto.Others.UpdateOthersDto;
import com.ratemyschools.rate.model.Other;
import com.ratemyschools.rate.model.School;
import com.ratemyschools.rate.repository.OtherRepository;
import com.ratemyschools.rate.repository.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OtherService {
    private final OtherRepository otherRepository;
    private final SchoolRepository schoolRepository;

    public OtherService(OtherRepository otherRepository, SchoolRepository schoolRepository) {
        this.otherRepository = otherRepository;
        this.schoolRepository = schoolRepository;
    }

    public List<OthersDto> getOthersInfo(Long schoolId) {
        List<Other> otherList = otherRepository.findBySchoolId(schoolId);

        return otherList.stream().map(other -> {
            OthersDto dto = new OthersDto();
            dto.setId(other.getId());
            dto.setCategory(other.getCategory());
            dto.setDescription(other.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public GetOthersCategoryDto getOtherCategory(Long id) {
        Optional<Other> otherOptional = otherRepository.findById(id);

        return otherOptional.map(other -> {
            GetOthersCategoryDto dto = new GetOthersCategoryDto();
            dto.setId(other.getId());
            dto.setCategory(other.getCategory());
            return dto;
        }).orElseGet(() -> {
           GetOthersCategoryDto dto = new GetOthersCategoryDto();
           dto.setId(id);
           dto.setCategory("Other categories not found");
           return dto;
        });
    }
    public Other addOthers(AddOthersDto input) {
        School school = schoolRepository.findById(input.getSchoolId())
                .orElseThrow(() -> new IllegalArgumentException("School not found with id: " + input.getSchoolId()));

        Other other = new Other();
        other.setSchool(school);
        other.setCategory(input.getCategory());
        other.setDescription(input.getDescription());
        return otherRepository.save(other);
    }

    public Other updateOthers(UpdateOthersDto input) {
        Optional<Other> optionalOther = otherRepository.findById(input.getId());

        if (optionalOther.isPresent()) {
            Other other = optionalOther.get();
            other.setCategory(input.getCategory());
            other.setDescription(input.getDescription());
            return otherRepository.save(other);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }
    public boolean deleteOthersById(Long id) {
        if (otherRepository.existsById(id)) {
            otherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
