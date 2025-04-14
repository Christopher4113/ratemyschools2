package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Housing.AddHousingDto;
import com.ratemyschools.rate.dto.Housing.GetHousingTypeDto;
import com.ratemyschools.rate.dto.Housing.HousingDto;
import com.ratemyschools.rate.dto.Housing.UpdateHousingDto;
import com.ratemyschools.rate.model.Housing;
import com.ratemyschools.rate.model.School;
import com.ratemyschools.rate.repository.HousingRepository;
import com.ratemyschools.rate.repository.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HousingService {
    private final HousingRepository housingRepository;
    private final SchoolRepository schoolRepository;

    public HousingService(HousingRepository housingRepository, SchoolRepository schoolRepository) {
        this.housingRepository = housingRepository;
        this.schoolRepository =schoolRepository;
    }

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

    public Housing addHousing(AddHousingDto input) {
        School school = schoolRepository.findById(input.getSchoolId())
                .orElseThrow(() -> new IllegalArgumentException("School not found with id: " + input.getSchoolId()));
        Housing housing = new Housing();
        housing.setSchool(school);
        housing.setType(housing.getType());
        housing.setDescription(housing.getDescription());
        return  housingRepository.save(housing);
    }

    public Housing updateHousing(UpdateHousingDto input) {
        Optional<Housing> optionalHousing = housingRepository.findById(input.getId());

        if (optionalHousing.isPresent()) {
            Housing housing = optionalHousing.get();
            housing.setType(input.getType());
            housing.setDescription(input.getDescription());
            return  housingRepository.save(housing);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }
    public boolean deleteHousingById(Long id) {
        if (housingRepository.existsById(id)) {
            housingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
