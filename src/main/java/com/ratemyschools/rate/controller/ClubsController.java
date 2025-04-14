package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.Clubs.AddClubsDto;
import com.ratemyschools.rate.dto.Clubs.ClubsDto;
import com.ratemyschools.rate.dto.Clubs.GetClubsCategoryDto;
import com.ratemyschools.rate.dto.Clubs.UpdateClubsDto;
import com.ratemyschools.rate.model.Clubs;
import com.ratemyschools.rate.service.ClubService;
import com.ratemyschools.rate.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class ClubsController {
    private final JwtService jwtService;
    private final ClubService clubService;

    public ClubsController(JwtService jwtService, ClubService clubService) {
        this.jwtService = jwtService;
        this.clubService = clubService;
    }

    @GetMapping("/getClubs/{prevId}")
    public List<ClubsDto> getClubsInfo(@PathVariable("prevId") Long id) {
        return clubService.getClubsInfo(id);
    }

    @GetMapping("/getClubName/{prevId}")
    public GetClubsCategoryDto getCategory(@PathVariable("prevId") Long id) {
        return clubService.getClubName(id);
    }

    @PostMapping("/postClubs")
    public ResponseEntity<Clubs> addClubs(@RequestBody AddClubsDto addClubsDto) {
        try {
            Clubs clubs = clubService.addClubs(addClubsDto);
            return  ResponseEntity.status(HttpStatus.CREATED).body(clubs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/updateClubs")
    public ResponseEntity<Clubs> updateClubs(@RequestBody UpdateClubsDto updateClubsDto) {
        try {
            Clubs updatedClubs = clubService.updateClubs(updateClubsDto);
            return  ResponseEntity.ok(updatedClubs);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteClubs/{id}")
    public ResponseEntity<String> deleteClubs(@PathVariable("id") Long id) {
        boolean deleted = clubService.deleteClubsById(id);
        if (deleted) {
            return ResponseEntity.ok("Clubs deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
