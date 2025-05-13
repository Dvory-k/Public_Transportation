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


import com.example.demo.DTOs.LineDTO;

import com.example.demo.Services.LineService;


@RestController
@RequestMapping("/lines")
public class LineController {

 
  @Autowired
  private  LineService lineService;
 
    @GetMapping("/getAll")
    public ResponseEntity<List<LineDTO>> getAll() {
        return ResponseEntity.ok().body(lineService.getAll());
    }


     @PostMapping("/addLine")
    public ResponseEntity<Long> create(@RequestBody LineDTO lineDTO) {
        Long result = lineService.addBus(lineDTO);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

    

}
