package com.ratemyschools.rate.controller;


import com.ratemyschools.rate.dto.LifeStyles.AddLifeStylesDto;
import com.ratemyschools.rate.dto.LifeStyles.GetLifeStylesCategoryDto;
import com.ratemyschools.rate.dto.LifeStyles.LifeStylesDto;
import com.ratemyschools.rate.dto.LifeStyles.UpdateLifeStylesDto;
import com.ratemyschools.rate.model.LifeStyle;
import com.ratemyschools.rate.service.JwtService;
import com.ratemyschools.rate.service.LifeStyleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/auth")
@RestController
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

    @PostMapping("/postLifeStyles")
    public ResponseEntity<LifeStyle> addLifeStyles(@RequestBody AddLifeStylesDto addLifeStylesDto) {
        try {
            LifeStyle lifeStyle = lifeStyleService.addLifeStyles(addLifeStylesDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(lifeStyle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/updateLifeStyles")
    public ResponseEntity<LifeStyle> updateLifeStyles(@RequestBody UpdateLifeStylesDto updateLifeStylesDto) {
        try {
            LifeStyle updatedLifeStyles = lifeStyleService.updateLifeStyles(updateLifeStylesDto);
            return ResponseEntity.ok(updatedLifeStyles);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteLifeStyles/{id}")
    public ResponseEntity<String> deleteLifeStyles(@PathVariable("id") Long id) {
        boolean deleted = lifeStyleService.deleteLifeStylesById(id);
        if (deleted) {
            return ResponseEntity.ok("LifeStyles deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
