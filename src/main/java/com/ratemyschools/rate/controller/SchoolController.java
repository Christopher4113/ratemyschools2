package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.SearchSchoolDto;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.SchoolService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class SchoolController {
    private final JwtService jwtService;

    private final SchoolService schoolService;

    public SchoolController(JwtService jwtService, SchoolService schoolService) {
        this.jwtService = jwtService;
        this.schoolService = schoolService;
    }
    // Endpoint to get all school names
    @GetMapping("/schools")
    public List<SearchSchoolDto> getAllSchoolNames() {
        return schoolService.getAllSchoolNames();
    }
}
