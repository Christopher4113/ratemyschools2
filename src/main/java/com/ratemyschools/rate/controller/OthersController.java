package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.Others.GetOthersCategoryDto;
import com.ratemyschools.rate.dto.Others.OthersDto;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.OtherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class OthersController {
    private final JwtService jwtService;
    private final OtherService otherService;

    public OthersController(JwtService jwtService, OtherService otherService) {
        this.jwtService = jwtService;
        this.otherService = otherService;
    }

    @GetMapping("/getOthers/{prevId}")
    public List<OthersDto> getOthersInfo(@PathVariable("prevId") Long id) {return otherService.getOthersInfo(id);}

    @GetMapping("/getOthersCategory/{prevId}")
    public GetOthersCategoryDto getCategory(@PathVariable("prevId") Long id) {return otherService.getOtherCategory(id);}
}
