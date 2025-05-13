package com.example.demo.Converters;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DTOs.LineDTO;

import com.example.demo.Models.Line;

import com.example.demo.Models.StationLine;
import com.example.demo.Models.Travel;
import com.example.demo.Repositories.StationLineRepository;

import com.example.demo.Repositories.TravelRepository;

public class LineConverter {
    
    @Autowired

 private  TravelRepository travelRepository;

     public  LineDTO toLineDTO(Line line) {
        LineDTO lineDTO  = new LineDTO();
        lineDTO.setId(line.getId());
        lineDTO.setNumber(line.getNumber());
        lineDTO.setSource(line.getSource());
        lineDTO.setDestination(line.getDestination());
        lineDTO.setTravelsIDs(line.getTravels().stream().map(Travel::getId).collect(Collectors.toList()));
        lineDTO.setStationsLinesNames(line.getStationsLines().stream().map(stationLine->stationLine.getStation().getName()).collect(Collectors.toList()));

        return lineDTO;
    }

    //המרת lineDTO לline
    // public static Line toLine(LineDTO lineDTO) {
    //     Line line = new Line();
    //     line.setId(lineDTO.getId());
    //     line.setNumber(lineDTO.getNumber());
    //     line.setSource(lineDTO.getSource());
    //     line.setDestination(lineDTO.getDestination());
    //     line.setTravels(lineDTO.getTravelsIDs().stream()
    //    .map(id -> travelRepository.findById(id).orElse(null)).collect(Collectors.toList()));
    //    line.setStationsLines(lineDTO.getStationsLinesIDs().stream()
    //    .map(id -> stationLineRepository.findById(id).orElse(null)).collect(Collectors.toList()));
    //    return line;
    // }

    public  Line toLine(LineDTO lineDTO) {
    Line line = new Line();
    line.setId(lineDTO.getId());
    line.setNumber(lineDTO.getNumber());
    line.setSource(lineDTO.getSource());
    line.setDestination(lineDTO.getDestination());

    // טיפול במצב שבו travelsIDs ריק או null
    List<Travel> travels = lineDTO.getTravelsIDs() != null
        ? lineDTO.getTravelsIDs().stream()
            .map(id -> travelRepository.findById(id).orElse(null))
            .collect(Collectors.toList())
        : List.of();
    line.setTravels(travels);

    // טיפול במצב שבו stationsLinesIDs ריק או null
    List<StationLine> stationLines = lineDTO.getStationsLinesIDs() != null
        ? lineDTO.getStationsLinesIDs().stream()
            .map(id -> stationLineRepository.findById(id).orElse(null))
            .collect(Collectors.toList())
        : List.of();
    line.setStationsLines(stationLines);

    return line;
}

 
    
//המרת ��שי��ת line לרשי��ת lineDTO
    public  List<LineDTO> toLineDTOList(List<Line> lines) {
        return lines.stream().map(line -> toLineDTO(line)).collect(Collectors.toList());
    }

    //המרת ��שי��ת lineDTO לרשי��ת line
    public  List<Line> toLineList(List<LineDTO> lineDTOs) {
        return lineDTOs.stream().map(line -> toLine(line)).collect(Collectors.toList());
    }
   
   

   


}
