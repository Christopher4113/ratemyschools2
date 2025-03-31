package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Clubs.ClubsDto;
import com.ratemyschools.rate.dto.Clubs.GetClubsCategoryDto;
import com.ratemyschools.rate.model.Clubs;
import com.ratemyschools.rate.repository.ClubsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClubService {
    private final ClubsRepository clubsRepository;

    public ClubService(ClubsRepository clubsRepository) {this.clubsRepository = clubsRepository;}

    public List<ClubsDto> getClubsInfo(Long schoolId) {
        List<Clubs> clubsList = clubsRepository.findBySchoolId(schoolId);

        return clubsList.stream().map(clubs -> {
            ClubsDto dto = new ClubsDto();
            dto.setId(clubs.getId());
            dto.setClubName(clubs.getClubName());
            dto.setDescription(clubs.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public GetClubsCategoryDto getClubName(Long id) {
        Optional<Clubs> clubsOptional = clubsRepository.findById(id);

        return clubsOptional.map(clubs -> {
            GetClubsCategoryDto dto = new GetClubsCategoryDto();
            dto.setId(clubs.getId());
            dto.setClubName(clubs.getClubName());
            return dto;
        }).orElseGet(() -> {
           GetClubsCategoryDto dto = new GetClubsCategoryDto();
           dto.setId(id);
           dto.setClubName("Club Names not found");
           return dto;
        });
    }
}
