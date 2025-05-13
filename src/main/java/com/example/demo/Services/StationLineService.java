package com.example.demo.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Converters.LineConverter;

import com.example.demo.Converters.StationLineConverter;
import com.example.demo.DTOs.LineDTO;
import com.example.demo.DTOs.StationLineDTO;
import com.example.demo.Models.Line;
import com.example.demo.Models.Station;
import com.example.demo.Models.StationLine;
import com.example.demo.Repositories.LineRepository;
import com.example.demo.Repositories.StationLineRepository;
import com.example.demo.Repositories.StationRepository;

import jakarta.transaction.Transactional;


@Service
public class StationLineService {

    @Autowired
    private  StationLineRepository stationLineRepository;
    @Autowired
    private  LineRepository lineRepository;
     @Autowired
    private  StationRepository stationRepository;
       @Autowired
    private  StationLineConverter stationLineConverter;
    @Autowired
    private  LineConverter lineConverter;

    // @Transactional
    // public Long addStationLine(StationLineDTO stationLine) {

    //     LineDTO lineDTO = lineConverter.toLineDTO(lineRepository.findByNumber(stationLine.getLineNumber()));
    //     for (Long sl : lineDTO.getStationsLinesIDs()) {
        
    //         StationLineDTO stationLineDTO = stationLineRepository.findById(sl)
    //                 .map(stationLineConverter::toStationLineDTO)
    //                .orElseThrow(() -> new RuntimeException("StationLine not found"));

    //     if(stationLineDTO.getStation_order() >= stationLine.getStation_order()){
    //         stationLineDTO.setStation_order(stationLineDTO.getStation_order() + 1);
    //         stationLineRepository.save(stationLineConverter.toStationLine(stationLineDTO));
    //     }
    // }

//     System.out.println("Updating stations with order >= " + stationLine.getStation_order());

// for (Long sl : lineDTO.getStationsLinesIDs()) {
//     StationLineDTO stationLineDTO = stationLineRepository.findById(sl)
//         .map(stationLineConverter::toStationLineDTO)
//         .orElseThrow();

//     System.out.println("Station: " + stationLineDTO.getStationName() + ", order: " + stationLineDTO.getStation_order());

//     if (stationLineDTO.getStation_order() >= stationLine.getStation_order()) {
//         stationLineDTO.setStation_order(stationLineDTO.getStation_order() + 1);
//         stationLineRepository.save(stationLineConverter.toStationLine(stationLineDTO));
//         System.out.println("Updated order to: " + stationLineDTO.getStation_order());
//     }
// }
//        return     stationLineRepository.save(stationLineConverter.toStationLine(stationLine)).getId();
@Transactional
public Long addStationLine(StationLineDTO stationLineDTO) {

    // שליפת הקו לפי מספר
    Line line = lineRepository.findByNumber(stationLineDTO.getLineNumber());
    if (line == null) {
        throw new RuntimeException("Line with number " + stationLineDTO.getLineNumber() + " not found");
    }

    // שליפת התחנה לפי שם
    Station station = stationRepository.findByName(stationLineDTO.getStationName());
    if (station == null) {
        throw new RuntimeException("Station '" + stationLineDTO.getStationName() + "' not found");
    }

    // שליפת כל תחנות הקו הזה לפי קו בלבד!
    List<StationLine> stationLinesForLine = stationLineRepository.findAll().stream()
        .filter(sl -> sl.getLine().getId() == line.getId())
        .collect(Collectors.toList());

    // הדפסת בדיקה
    System.out.println("Updating stations with order >= " + stationLineDTO.getStation_order());

    // הזזה קדימה של תחנות קיימות מהסדר הנתון ומעלה
    for (StationLine sl : stationLinesForLine) {
        if (sl.getStation_order() >= stationLineDTO.getStation_order()) {
            sl.setStation_order(sl.getStation_order() + 1);
            stationLineRepository.save(sl);
        }
    }

    // יצירת תחנה חדשה במיקום הנכון
    StationLine newStationLine = new StationLine();
    newStationLine.setLine(line);
    newStationLine.setStation(station);
    newStationLine.setStation_order(stationLineDTO.getStation_order());

    stationLineRepository.save(newStationLine);

    return newStationLine.getId();
}



  public List<StationLineDTO> getAll()
    {
    return stationLineRepository.findAll().stream()
    .map(stationLineConverter::toStationLineDTO)
    .collect((Collectors.toList()));
    }


}
