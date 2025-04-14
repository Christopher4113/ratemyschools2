package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Athletics.AddAthleticsDto;
import com.ratemyschools.rate.dto.Athletics.AthleticsDto;
import com.ratemyschools.rate.dto.Athletics.GetAthleticsCategoryDto;
import com.ratemyschools.rate.dto.Athletics.UpdateAthleticsDto;
import com.ratemyschools.rate.model.Athletics;
import com.ratemyschools.rate.model.School;
import com.ratemyschools.rate.repository.AthleticsRepository;
import com.ratemyschools.rate.repository.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AthleticService {
    private final AthleticsRepository athleticsRepository;
    private final SchoolRepository schoolRepository;

    public AthleticService(AthleticsRepository athleticsRepository , SchoolRepository schoolRepository) {
        this.athleticsRepository = athleticsRepository;
        this.schoolRepository = schoolRepository;
    }

    public List<AthleticsDto> getAthleticsInfo(Long schoolId) {
        List<Athletics> athleticsList = athleticsRepository.findBySchoolId(schoolId);

        return athleticsList.stream().map(athletics -> {
            AthleticsDto dto = new AthleticsDto();
            dto.setId(athletics.getId());
            dto.setCategory(athletics.getCategory());
            dto.setDescription(athletics.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public GetAthleticsCategoryDto getCategory(Long id) {
        Optional<Athletics> athleticsOptional = athleticsRepository.findById(id);

        // Return a DTO with the category or an empty category if not found
        return athleticsOptional.map(athletics -> {
            GetAthleticsCategoryDto dto = new GetAthleticsCategoryDto();
            dto.setId(athletics.getId());
            dto.setCategory(athletics.getCategory());
            return dto;
        }).orElseGet(() -> {
            GetAthleticsCategoryDto dto = new GetAthleticsCategoryDto();
            dto.setId(id);  // Set the id if not found (could be a placeholder for "not found")
            dto.setCategory("Category not found");
            return dto;
        });
    }
    public Athletics addAthletics(AddAthleticsDto input) {
        School school = schoolRepository.findById(input.getSchoolId())
                .orElseThrow(() -> new IllegalArgumentException("School not found with id: " + input.getSchoolId()));

        Athletics athletics = new Athletics();
        athletics.setSchool(school);
        athletics.setCategory(input.getCategory());
        athletics.setDescription(input.getDescription());

        return athleticsRepository.save(athletics);
    }

    public Athletics updateAthletics(UpdateAthleticsDto input) {
        Optional<Athletics> optionalAthletics = athleticsRepository.findById(input.getId());

        if(optionalAthletics.isPresent()) {
            Athletics athletics = optionalAthletics.get();
            athletics.setCategory(input.getCategory());
            athletics.setDescription(input.getDescription());
            return athleticsRepository.save(athletics);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }

    public boolean deleteAthleticsById(Long id) {
        if (athleticsRepository.existsById(id)) {
            athleticsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
