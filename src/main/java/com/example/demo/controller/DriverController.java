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

import com.example.demo.DTOs.DriverDTO;

import com.example.demo.Services.DriverService;


@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private  DriverService driverService;
    

 
    @GetMapping("/getAll")
    public ResponseEntity<List<DriverDTO>> getAll() {
        return ResponseEntity.ok().body(driverService.getAll());
    }


     @PostMapping("/adddriver")
    public ResponseEntity<Long> create(@RequestBody DriverDTO driverDTO) {
        Long result = driverService.addDriver(driverDTO);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

    

}
