package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTOs.StationDTO;

import com.example.demo.Services.StationService;


@RestController
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private  StationService stationService;
    

 
    @GetMapping("/getAll")
    public ResponseEntity<List<StationDTO>> getAll() {
        return ResponseEntity.ok().body(stationService.getAll());
    }


     @PostMapping("/add")
    public ResponseEntity<Long> create(@RequestBody StationDTO stationDTO) {
        Long result = stationService.add(stationDTO);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

    

}
