package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.Majors.AddMajorsDto;
import com.ratemyschools.rate.dto.Majors.GetMajorsCategoryDto;
import com.ratemyschools.rate.dto.Majors.MajorsDto;
import com.ratemyschools.rate.dto.Majors.UpdateMajorsDto;
import com.ratemyschools.rate.model.Major;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.MajorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/auth")
@RestController
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

    @PostMapping("/postMajors")
    public ResponseEntity<Major> addMajors(@RequestBody AddMajorsDto addMajorsDto) {
        try {
            Major major = majorService.addMajors(addMajorsDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(major);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/updateMajors")
    public ResponseEntity<Major> updateMajors(@RequestBody UpdateMajorsDto updateMajorsDto) {
        try {
            Major updatedMajors = majorService.updateMajors(updateMajorsDto);
            return ResponseEntity.ok(updatedMajors);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteMajors/{id}")
    public ResponseEntity<String> deleteMajors(@PathVariable("id") Long id) {
        boolean deleted = majorService.deleteMajorsById(id);
        if (deleted) {
            return ResponseEntity.ok("Majors deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
