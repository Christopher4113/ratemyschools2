package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.LifeStyles.AddLifeStylesDto;
import com.ratemyschools.rate.dto.LifeStyles.GetLifeStylesCategoryDto;
import com.ratemyschools.rate.dto.LifeStyles.LifeStylesDto;
import com.ratemyschools.rate.dto.LifeStyles.UpdateLifeStylesDto;
import com.ratemyschools.rate.model.LifeStyle;
import com.ratemyschools.rate.model.School;
import com.ratemyschools.rate.repository.LifeStylesRepository;
import com.ratemyschools.rate.repository.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LifeStyleService {
    private final LifeStylesRepository lifeStylesRepository;
    private final SchoolRepository schoolRepository;

    public LifeStyleService(LifeStylesRepository lifeStylesRepository, SchoolRepository schoolRepository) {
        this.lifeStylesRepository = lifeStylesRepository;
        this.schoolRepository = schoolRepository;
    }

    public List<LifeStylesDto> getLifeStylesInfo(Long schoolId) {
        List<LifeStyle> lifeStyleList = lifeStylesRepository.findBySchoolId(schoolId);

        return lifeStyleList.stream().map(lifeStyle -> {
            LifeStylesDto dto = new LifeStylesDto();
            dto.setId(lifeStyle.getId());
            dto.setCategory(lifeStyle.getCategory());
            dto.setDescription(lifeStyle.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public GetLifeStylesCategoryDto getCategory(Long id) {
        Optional<LifeStyle> lifeStyleOptional = lifeStylesRepository.findById(id);

        return lifeStyleOptional.map(lifeStyle -> {
            GetLifeStylesCategoryDto dto = new GetLifeStylesCategoryDto();
            dto.setId(lifeStyle.getId());
            dto.setCategory(lifeStyle.getCategory());
            return dto;
        }).orElseGet(() -> {
           GetLifeStylesCategoryDto dto = new GetLifeStylesCategoryDto();
           dto.setId(id);
           dto.setCategory("Category not found");
           return dto;
        });
    }

    public LifeStyle addLifeStyles(AddLifeStylesDto input) {
        School school = schoolRepository.findById(input.getSchoolId())
                .orElseThrow(() -> new IllegalArgumentException("School not found with id: " + input.getSchoolId()));

        LifeStyle lifeStyle = new LifeStyle();
        lifeStyle.setSchool(school);
        lifeStyle.setCategory(input.getCategory());
        lifeStyle.setDescription(input.getDescription());
        return lifeStylesRepository.save(lifeStyle);
    }

    public LifeStyle updateLifeStyles(UpdateLifeStylesDto input) {
        Optional<LifeStyle> optionalLifeStyle = lifeStylesRepository.findById(input.getId());
         if (optionalLifeStyle.isPresent()) {
             LifeStyle lifeStyle = optionalLifeStyle.get();
             lifeStyle.setCategory(input.getCategory());
             lifeStyle.setDescription(input.getDescription());
             return  lifeStylesRepository.save(lifeStyle);
         } else {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
         }
    }

    public boolean deleteLifeStylesById(Long id) {
        if (lifeStylesRepository.existsById(id)) {
            lifeStylesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
