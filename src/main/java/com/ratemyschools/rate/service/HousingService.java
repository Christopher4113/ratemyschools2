package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Housing.GetHousingTypeDto;
import com.ratemyschools.rate.dto.Housing.HousingDto;
import com.ratemyschools.rate.model.Housing;
import com.ratemyschools.rate.repository.HousingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HousingService {
    private final HousingRepository housingRepository;

    public HousingService(HousingRepository housingRepository) {this.housingRepository = housingRepository;}

    public List<HousingDto> getHousingInfo(Long schoolId) {
        List<Housing> housingList = housingRepository.findBySchoolId(schoolId);

        return housingList.stream().map(housing -> {
            HousingDto dto = new HousingDto();
            dto.setId(housing.getId());
            dto.setType(housing.getType());
            dto.setDescription(housing.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public GetHousingTypeDto getHousingType(Long id) {
        Optional<Housing> housingOptional = housingRepository.findById(id);

        return housingOptional.map(housing -> {
            GetHousingTypeDto dto = new GetHousingTypeDto();
            dto.setId(housing.getId());
            dto.setType(housing.getType());
            return dto;
        }).orElseGet(() -> {
            GetHousingTypeDto dto = new GetHousingTypeDto();
            dto.setId(id);
            dto.setType("Housing Types were not found");
            return dto;
        });
    }
}
