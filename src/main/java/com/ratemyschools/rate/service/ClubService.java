package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Clubs.AddClubsDto;
import com.ratemyschools.rate.dto.Clubs.ClubsDto;
import com.ratemyschools.rate.dto.Clubs.GetClubsCategoryDto;
import com.ratemyschools.rate.dto.Clubs.UpdateClubsDto;
import com.ratemyschools.rate.model.Clubs;
import com.ratemyschools.rate.model.School;
import com.ratemyschools.rate.repository.ClubsRepository;

import com.ratemyschools.rate.repository.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClubService {
    private final ClubsRepository clubsRepository;
    private final SchoolRepository schoolRepository;

    public ClubService(ClubsRepository clubsRepository, SchoolRepository schoolRepository) {
        this.clubsRepository = clubsRepository;
        this.schoolRepository = schoolRepository;
    }

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

    public Clubs addClubs(AddClubsDto input) {
        School school = schoolRepository.findById(input.getSchoolId())
                .orElseThrow(() -> new IllegalArgumentException("School not found with id: " + input.getSchoolId()));
        Clubs clubs = new Clubs();
        clubs.setSchool(school);
        clubs.setClubName(input.getClubName());
        clubs.setDescription(input.getDescription());
        return clubsRepository.save(clubs);
    }

    public Clubs updateClubs(UpdateClubsDto input) {
        Optional<Clubs> optionalClubs = clubsRepository.findById(input.getId());

        if(optionalClubs.isPresent()) {
            Clubs clubs = optionalClubs.get();
            clubs.setClubName(input.getClubName());
            clubs.setDescription(input.getDescription());
            return  clubsRepository.save(clubs);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }
    public boolean deleteClubsById(Long id) {
        if (clubsRepository.existsById(id)) {
            clubsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
