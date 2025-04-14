package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.Others.AddOthersDto;
import com.ratemyschools.rate.dto.Others.GetOthersCategoryDto;
import com.ratemyschools.rate.dto.Others.OthersDto;
import com.ratemyschools.rate.dto.Others.UpdateOthersDto;
import com.ratemyschools.rate.model.Other;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.OtherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/postOthers")
    public ResponseEntity<Other> addOthers(@RequestBody AddOthersDto addOthersDto) {
        try {
            Other other = otherService.addOthers(addOthersDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(other);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/updateOthers")
    public ResponseEntity<Other> updateOthers(@RequestBody UpdateOthersDto updateOthersDto) {
        try {
            Other updatedOthers = otherService.updateOthers(updateOthersDto);
            return ResponseEntity.ok(updatedOthers);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteOthers/{id}")
    public ResponseEntity<String> deleteOthers(@PathVariable("id") Long id) {
        boolean deleted = otherService.deleteOthersById(id);
        if (deleted) {
            return ResponseEntity.ok("Others deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
