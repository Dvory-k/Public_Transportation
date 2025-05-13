package com.example.demo.Converters;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DTOs.StationLineDTO;
import com.example.demo.Models.Line;
import com.example.demo.Models.Station;
import com.example.demo.Models.StationLine;
import com.example.demo.Repositories.LineRepository;
import com.example.demo.Repositories.StationRepository;


public class StationLineConverter {
    
    
@Autowired
 private  LineRepository lineRepository;
 @Autowired
 private  StationRepository stationRepository;

//המרת stationLine לstationLineDto
    public  StationLineDTO toStationLineDTO(StationLine stationLine) {
        StationLineDTO stationLineDTO = new StationLineDTO();
        stationLineDTO.setId(stationLine.getId());
        stationLineDTO.setLineNumber(stationLine.getLine().getNumber());
        stationLineDTO.setStationName(stationLine.getStation().getName());
        stationLine.setStation_order(stationLine.getStation_order());   
        return stationLineDTO;
}

    //המרת stationLineDTO לstationLine
    public  StationLine toStationLine(StationLineDTO stationLineDTO) {
        StationLine stationLine = new StationLine();
        stationLine.setId(stationLineDTO.getId());
        Line line = lineRepository.findByNumber(stationLineDTO.getLineNumber());
        Station station = stationRepository.findByName(stationLineDTO.getStationName());
        stationLine.setLine(line);
        stationLine.setStation(station);
        stationLine.setStation_order(stationLineDTO.getStation_order());
        return stationLine;
    }
    //המרת ��שי��ת stationLine לרשי��ת stationLineDTO
    public  List<StationLineDTO> toStationLineDTOList(List<StationLine> stationLines) {
        return stationLines.stream().map(stationLine -> toStationLineDTO(stationLine)).collect(Collectors.toList());
    }
    //המרת ��שי��ת stationLine לרשי��ת stationLine
    public  List<StationLine> toStationLineList(List<StationLineDTO> stationLineDTOs) {
        return stationLineDTOs.stream().map(stationLine -> toStationLine(stationLine)).collect(Collectors.toList());
    }
    }



   