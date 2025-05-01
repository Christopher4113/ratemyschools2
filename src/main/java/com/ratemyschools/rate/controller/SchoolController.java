package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.IndividualSchoolDto;
import com.ratemyschools.rate.dto.School.AddSchoolDto;
import com.ratemyschools.rate.dto.School.UpdateSchoolDto;
import com.ratemyschools.rate.dto.SearchSchoolDto;
import com.ratemyschools.rate.model.School;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/auth")
@RestController
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

    @GetMapping("/getSchool/{id}")
    public IndividualSchoolDto getIndividualSchool(@PathVariable Long id) {
       return schoolService.getIndividualSchool(id);
    }

    @PostMapping("/postSchool")
    public ResponseEntity<School> addSchool(@RequestBody AddSchoolDto addSchoolDto) {
        try {
            School school = schoolService.addSchool(addSchoolDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(school);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/updateSchool")
    public ResponseEntity<School> updateSchool(@RequestBody UpdateSchoolDto updateSchoolDto) {
        try {
            School updatedSchool = schoolService.updateSchool(updateSchoolDto);
            return ResponseEntity.ok(updatedSchool);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/deleteSchool/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        boolean deleted = schoolService.deleteSchoolById(id);
        if (deleted) {
            return ResponseEntity.ok("School deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
