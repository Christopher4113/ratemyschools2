package com.ratemyschools.rate.controller;


import com.ratemyschools.rate.dto.Housing.AddHousingDto;
import com.ratemyschools.rate.dto.Housing.GetHousingTypeDto;
import com.ratemyschools.rate.dto.Housing.HousingDto;
import com.ratemyschools.rate.dto.Housing.UpdateHousingDto;
import com.ratemyschools.rate.model.Housing;
import com.ratemyschools.rate.service.HousingService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/auth")
@RestController
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

    @PostMapping("/postHousing")
    public ResponseEntity<Housing> addHousing(@RequestBody AddHousingDto addHousingDto) {
        try {
            Housing housing = housingService.addHousing(addHousingDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(housing);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/updateHousing")
    public  ResponseEntity<Housing> updateHousing(@RequestBody UpdateHousingDto updateHousingDto) {
        try {
            Housing updatedHousing = housingService.updateHousing(updateHousingDto);
            return  ResponseEntity.ok(updatedHousing);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteHousing/{id}")
    public ResponseEntity<String> deleteHousing(@PathVariable("id") Long id) {
        boolean deleted = housingService.deleteHousingById(id);
        if (deleted) {
            return ResponseEntity.ok("Housing deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
