package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.Athletics.AddAthleticsDto;
import com.ratemyschools.rate.dto.Athletics.AthleticsDto;
import com.ratemyschools.rate.dto.Athletics.GetAthleticsCategoryDto;
import com.ratemyschools.rate.dto.Athletics.UpdateAthleticsDto;
import com.ratemyschools.rate.model.Athletics;
import com.ratemyschools.rate.service.AthleticService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/auth")
@RestController
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

    @GetMapping("/getCategory/{prevId}")
    public GetAthleticsCategoryDto getCategory(@PathVariable("prevId") Long id) {
        return athleticService.getCategory(id);
    }

    @PostMapping("/postAthletics")
    public ResponseEntity<Athletics> addAthletics(@RequestBody AddAthleticsDto addAthleticsDto) {
        try {
            Athletics athletics = athleticService.addAthletics(addAthleticsDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(athletics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateAthletics")
    public ResponseEntity<Athletics> updateAthletics(@RequestBody UpdateAthleticsDto updateAthleticsDto) {
        try {
            Athletics updatedAthletics = athleticService.updateAthletics(updateAthleticsDto);
            return  ResponseEntity.ok(updatedAthletics);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteAthletics/{id}")
    public ResponseEntity<String> deleteAthletics(@PathVariable("id") Long id) {
        boolean deleted = athleticService.deleteAthleticsById(id);
        if (deleted) {
            return ResponseEntity.ok("Athletics deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
