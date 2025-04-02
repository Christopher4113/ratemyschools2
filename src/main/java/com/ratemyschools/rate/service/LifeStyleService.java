package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.LifeStyles.GetLifeStylesCategoryDto;
import com.ratemyschools.rate.dto.LifeStyles.LifeStylesDto;
import com.ratemyschools.rate.model.LifeStyle;
import com.ratemyschools.rate.repository.LifeStylesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LifeStyleService {
    private final LifeStylesRepository lifeStylesRepository;

    public LifeStyleService(LifeStylesRepository lifeStylesRepository) {this.lifeStylesRepository = lifeStylesRepository;}

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
}
