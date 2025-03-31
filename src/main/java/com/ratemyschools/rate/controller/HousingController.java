package com.ratemyschools.rate.controller;


import com.ratemyschools.rate.dto.Housing.GetHousingTypeDto;
import com.ratemyschools.rate.dto.Housing.HousingDto;
import com.ratemyschools.rate.service.HousingService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class HousingController {
    private final JwtService jwtService;
    private final HousingService housingService;

    public HousingController(JwtService jwtService, HousingService housingService) {
        this.jwtService = jwtService;
        this.housingService = housingService;
    }
    @GetMapping("/getHousing/{prevId}")
    public List<HousingDto> getHousingInfo(@PathVariable("prevId") Long id) {
        return housingService.getHousingInfo(id);
    }

    @GetMapping("/getHousingType/{prevId}")
    public GetHousingTypeDto getType(@PathVariable("prevId") Long id) {
        return housingService.getHousingType(id);
    }
}
