package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.AthleticsDto;
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
            return dto;
        }).collect(Collectors.toList());
    }
}
