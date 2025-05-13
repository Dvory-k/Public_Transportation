package com.example.demo.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Converters.LineConverter;

import com.example.demo.Converters.StationLineConverter;
import com.example.demo.DTOs.LineDTO;
import com.example.demo.DTOs.StationLineDTO;

import com.example.demo.Repositories.LineRepository;
import com.example.demo.Repositories.StationLineRepository;


@Service
public class StationLineService {

    @Autowired
    private  StationLineRepository stationLineRepository;
    @Autowired
    private  LineRepository lineRepository;
       @Autowired
    private  StationLineConverter stationLineConverter;
    @Autowired
    private  LineConverter lineConverter;

    public Long addStationLine(StationLineDTO stationLine) {

        LineDTO lineDTO = lineConverter.toLineDTO(lineRepository.findByNumber(stationLine.getLineNumber()));
        for (Long sl : lineDTO.getStationsLinesIDs()) {
        
            StationLineDTO stationLineDTO = stationLineRepository.findById(sl)
                    .map(stationLineConverter::toStationLineDTO)
                    .orElse(new StationLineDTO());

        if(stationLineDTO.getStation_order() >= stationLine.getStation_order()){
            stationLineDTO.setStation_order(stationLineDTO.getStation_order() + 1);
            stationLineRepository.save(stationLineConverter.toStationLine(stationLineDTO));
        }
    }
       return     stationLineRepository.save(stationLineConverter.toStationLine(stationLine)).getId();
}

  public List<StationLineDTO> getAll()
    {
    return stationLineRepository.findAll().stream()
    .map(stationLineConverter::toStationLineDTO)
    .collect((Collectors.toList()));
    }


}
