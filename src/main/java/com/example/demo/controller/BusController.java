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

import com.example.demo.DTOs.BusDTO;

import com.example.demo.Services.BusService;


@RestController
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private  BusService busService;
    

 
    @GetMapping("/getAll")
    public ResponseEntity<List<BusDTO>> getAll() {
        return ResponseEntity.ok().body(busService.getAll());
    }


     @PostMapping("/addbus")
    public ResponseEntity<Long> create(@RequestBody BusDTO busDTO) {
        Long result = busService.addBus(busDTO);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

    

}
