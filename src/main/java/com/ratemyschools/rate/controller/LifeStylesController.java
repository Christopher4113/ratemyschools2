package com.ratemyschools.rate.controller;


import com.ratemyschools.rate.dto.LifeStyles.GetLifeStylesCategoryDto;
import com.ratemyschools.rate.dto.LifeStyles.LifeStylesDto;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.LifeStyleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class LifeStylesController {
    private final JwtService jwtService;
    private final LifeStyleService lifeStyleService;

    public LifeStylesController(JwtService jwtService, LifeStyleService lifeStyleService) {
        this.jwtService = jwtService;
        this.lifeStyleService = lifeStyleService;
    }

    @GetMapping("/getLifeStyles/{prevId}")
    public List<LifeStylesDto> getLifeStylesInfo(@PathVariable("prevId") Long id) {
        return lifeStyleService.getLifeStylesInfo(id);
    }

    @GetMapping("/getLifeStylesCategory/{prevId}")
    public GetLifeStylesCategoryDto getCategory(@PathVariable("prevId") Long id) {
        return lifeStyleService.getCategory(id);
    }
}
