package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTOs.TravelDTO;
import com.example.demo.Services.TravelService;

@RestController
@RequestMapping("/travels")
public class TravelController {

    @Autowired
    private  TravelService travelService;
    
     @PostMapping("/add")
    public ResponseEntity<Long> create(@RequestBody TravelDTO travelDTO) {
        Long result = travelService.add(travelDTO);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

}
