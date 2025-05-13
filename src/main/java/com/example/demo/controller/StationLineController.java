package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.StationLineDTO;

import com.example.demo.Services.StationLineService;


@RestController
@RequestMapping("/stationLine")
public class StationLineController {

    @Autowired
    private  StationLineService stationLineService;
    
     @PostMapping("/add")
    public ResponseEntity<Long> create(@RequestBody StationLineDTO stationLineDTO) {
        Long result = stationLineService.addStationLine(stationLineDTO);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

        @GetMapping("/getAll")
    public ResponseEntity<List<StationLineDTO>> getAll() {
        return ResponseEntity.ok().body(stationLineService.getAll());
    }

      @DeleteMapping("/byId/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (stationLineService.remove(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }
    

}
