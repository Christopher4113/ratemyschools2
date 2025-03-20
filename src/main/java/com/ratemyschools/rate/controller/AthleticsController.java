package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.AthleticsDto;
import com.ratemyschools.rate.service.AthleticService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class AthleticsController {
    private final JwtService jwtService;

    private final AthleticService athleticService;

    public AthleticsController(JwtService jwtService, AthleticService athleticService) {
        this.jwtService = jwtService;
        this.athleticService = athleticService;
    }

    @GetMapping("/getAthletics/{prevId}")
    public List<AthleticsDto> getAthleticsInfo(@PathVariable("prevId") Long id) {
        return athleticService.getAthleticsInfo(id);
    }

}
