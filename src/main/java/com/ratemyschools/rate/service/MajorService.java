package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Majors.GetMajorsCategoryDto;
import com.ratemyschools.rate.dto.Majors.MajorsDto;
import com.ratemyschools.rate.model.Major;
import com.ratemyschools.rate.repository.MajorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MajorService {
    private final MajorRepository majorRepository;

    public MajorService(MajorRepository majorRepository) {this.majorRepository = majorRepository;}

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
}
