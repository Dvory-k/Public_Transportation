package com.example.demo.Converters;

import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.DTOs.StationDTO;
import com.example.demo.Models.Station;


public class StationConverter {

//המרת station לstationDto
    public  StationDTO toStationDTO(Station station) {
        StationDTO stationDTO = new StationDTO();
        stationDTO.setId(station.getId());
        stationDTO.setName(station.getName());
        stationDTO.setStationsLinesNumbers(station.getStationLines().stream().map(stationLine-> stationLine.getLine().getNumber()).collect(Collectors.toList()));

        return stationDTO;
    }

    //המרת stationDto לstation
    public  Station toStation(StationDTO stationDTO) {
        Station station = new Station();
        station.setId(stationDTO.getId());
        station.setName(stationDTO.getName());
    //     station.setStationLines(stationDTO.getStationsLinesIDs().stream()
    //    .map(id -> stationLineRepository.findById(id).orElse(null)).collect(Collectors.toList()));
       return station;
    }
    //המרת ��שי��ת station לרשי��ת stationDto
    public  List<StationDTO> toStationDTOList(List<Station> stations) {
        return stations.stream().map(station -> toStationDTO(station)).collect(Collectors.toList());
    }
    //המרת ��שי��ת stationDto לרשי��ת station
    public  List<Station> toStationList(List<StationDTO> stationDTOs) {
        return stationDTOs.stream().map(station-> toStation(station)).collect(Collectors.toList());
    }
 
}

   