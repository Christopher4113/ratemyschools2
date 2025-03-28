package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.Clubs.ClubsDto;
import com.ratemyschools.rate.dto.Clubs.GetClubsCategoryDto;
import com.ratemyschools.rate.service.ClubService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class ClubsController {
    private final JwtService jwtService;
    private final ClubService clubService;

    public ClubsController(JwtService jwtService, ClubService clubService) {
        this.jwtService = jwtService;
        this.clubService = clubService;
    }

    @GetMapping("/getClubs/{prevId}")
    public List<ClubsDto> getClubsInfo(@PathVariable("prevId") Long id) {
        return clubService.getClubsInfo(id);
    }

    @GetMapping("/getClubName/{prevId}")
    public GetClubsCategoryDto getCategory(@PathVariable("prevId") Long id) {
        return clubService.getClubName(id);
    }

}
