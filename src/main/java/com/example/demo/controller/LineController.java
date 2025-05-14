package com.example.demo.controller;

import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTOs.LineDTO;
import com.example.demo.Services.LineService;

@RestController
@RequestMapping("/lines")
public class LineController {

  @Autowired
  private  LineService lineService;
 
    @GetMapping("/getBusPlaces{lineNumber}")
    public ResponseEntity<String> getBusPlaces(String lineNumber) {
        return ResponseEntity.ok().body(lineService.getBusPlaces(lineNumber));
    }

       @GetMapping("/getBusStations{lineNumber}")
    public ResponseEntity<String> getBusStations(String lineNumber) {
        return ResponseEntity.ok().body(lineService.getBusStations(lineNumber));
    }

        @GetMapping("/getAllTravels{lineNumber}")
    public ResponseEntity<List<LocalTime>> getAllTravels(String lineNumber) {
        return ResponseEntity.ok().body(lineService.getAllTravels(lineNumber));
    }
    @GetMapping("/getTravelsByHours")
public ResponseEntity<List<LocalTime>> getTravelsByHours(
        @RequestParam String lineNumber,
        @RequestParam String hour) {

    LocalTime parsedHour = LocalTime.parse(hour); // פורמט ISO: "HH:mm:ss"
    return ResponseEntity.ok().body(lineService.getTravelsByHours(lineNumber, parsedHour));
}

    @GetMapping("/getAll")
    public ResponseEntity<List<LineDTO>> getAll() {
        return ResponseEntity.ok().body(lineService.getAll());
    }

     @PostMapping("/add")
    public ResponseEntity<Long> create(@RequestBody LineDTO lineDTO) {
        Long result = lineService.addBus(lineDTO);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

}
