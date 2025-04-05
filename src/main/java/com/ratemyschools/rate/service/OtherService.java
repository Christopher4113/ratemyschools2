package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Others.GetOthersCategoryDto;
import com.ratemyschools.rate.dto.Others.OthersDto;
import com.ratemyschools.rate.model.Other;
import com.ratemyschools.rate.repository.OtherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OtherService {
    private final OtherRepository otherRepository;

    public OtherService(OtherRepository otherRepository) {this.otherRepository = otherRepository;}

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
}
