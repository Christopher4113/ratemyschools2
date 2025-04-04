package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.Majors.GetMajorsCategoryDto;
import com.ratemyschools.rate.dto.Majors.MajorsDto;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.MajorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class MajorsController {
    private final JwtService jwtService;
    private final MajorService majorService;

    public MajorsController(JwtService jwtService, MajorService majorService) {
        this.jwtService = jwtService;
        this.majorService = majorService;
    }

    @GetMapping("/getMajors/{prevId}")
    public List<MajorsDto> getMajorsInfo(@PathVariable("prevId") Long id) {return majorService.getMajorsInfo(id);}

    @GetMapping("/getMajorsName/{prevId}")
    public GetMajorsCategoryDto getCategory(@PathVariable("prevId") Long id) {return majorService.getMajorName(id);}

}
