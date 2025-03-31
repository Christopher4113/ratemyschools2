package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Athletics.AthleticsDto;
import com.ratemyschools.rate.dto.Athletics.GetAthleticsCategoryDto;
import com.ratemyschools.rate.model.Athletics;
import com.ratemyschools.rate.repository.AthleticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AthleticService {
    private final AthleticsRepository athleticsRepository;

    public AthleticService(AthleticsRepository athleticsRepository) {this.athleticsRepository = athleticsRepository;}

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
}
