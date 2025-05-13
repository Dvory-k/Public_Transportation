package com.example.demo.Services;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.Converters.LineConverter;
import com.example.demo.Converters.StationConverter;
import com.example.demo.Converters.StationLineConverter;
import com.example.demo.DTOs.StationDTO;
import com.example.demo.DTOs.StationLineDTO;
import com.example.demo.Models.Line;
import com.example.demo.Models.Station;
import com.example.demo.Models.StationLine;
import com.example.demo.Models.Travel;
import com.example.demo.Repositories.LineRepository;
import com.example.demo.Repositories.StationLineRepository;
import com.example.demo.Repositories.StationRepository;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private StationConverter stationConverter;

    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private LineConverter lineConverter;

    @Autowired
    private StationLineRepository stationLinerRepository;
    @Autowired
    private StationLineConverter stationLineConverter;

//     public String SearchByStation(Long stationNumber, String lineNumber) {
//     // חיפוש תחנה
//     Station station = stationRepository.findById(stationNumber).orElse(null);
//     if (station == null) {
//         return "Station not found";
//     }

//     // חיפוש קו
//     Line line = lineRepository.findByNumber(lineNumber);
//     if (line == null) {
//         return "Line not found";
//     }

//     // המרת Station ל-DTO
    
//     StationDTO stationDTO = stationConverter.toStationDTO(station);

//     // שליפת כל נסיעות הקו
//     List<Travel> travels = line.getTravels();
//     Time currentTime = Time.valueOf(LocalTime.now());

//     // מיון לפי זמן יציאה
//     travels.sort(Comparator.comparingLong(
//         travel -> Math.abs(currentTime.getTime() - travel.getDepature_time().getTime())));

//     // החזרת זמני הגעה
//     String arrivelTimes = "קו " + line.getNumber() + " - יגיע לתחנה " + station.getName() + "\n";

//     // שליפת ה-StationLine המתאים
//     StationLine stationLineDTO = stationDTO.getStationsLinesNumbers().stream()
//         .filter(stationL -> stationL.getLineNumber().equals(lineNumber))
//         .findFirst()
//         .orElse(null);

//     if (stationLineDTO == null) {
//         return "StationLine not found for the given line";
//     }

//     // בדיקת זמן
//     for (Travel travel : travels) {
//         LocalTime departureLocalTime = travel.getDepature_time().toLocalTime();
//         LocalTime arrivalLocalTime = departureLocalTime.plusMinutes(stationLineDTO.getStation_order());
//         Time estimatedArrivalTime = Time.valueOf(arrivalLocalTime);

         
// if (currentTime.compareTo(estimatedArrivalTime) <= 0) {
//                 long diffInMillis = estimatedArrivalTime.getTime() - currentTime.getTime();
//                 long diffMinutes = diffInMillis / (1000 * 60);
//                 arrivelTimes += " יגיע לתחנה בעוד: " + diffMinutes + " דקות\n";
//             }
//     }

//     return arrivelTimes;
// }


public String SearchByStation(Long stationNumber, String lineNumber) {
        // חיפוש תחנה
        Station station = stationRepository.findById(stationNumber).orElse(null);
        if (station == null) {
            return "Station not found";
        }
        // חיפוש קו
        Line line = lineRepository.findByNumber(lineNumber);
        if (line == null) {
            return "Line not found";
        }
        // שליפת כל נסיעות הקו
        List<Travel> travels = line.getTravels();

        Time currentTime = Time.valueOf(LocalTime.now());
        // מיון לפי זמן יציאה
        travels.sort(Comparator.comparingLong(
                travel -> Math.abs(currentTime.getTime() - travel.getDepature_time().getTime())));
        // החזרת זמני הגעה
        String arrivelTimes = "קו " + line.getNumber() + " - יגיע לתחנה " + station.getName() + "\n";

        // שליפת ה stationLine

        StationLine stationLine = station.getStationLines().stream()
                .filter(stationL -> stationL.getLine().getNumber().equals(lineNumber.toString())).findFirst().orElse(null);
                if(stationLine == null) {
                    return "StationLine not found for the given line";
                }

        // StationLine stationLine = stationLinerRepository.findByStationAndLine(station.getId(), line.getId());
        //         if(stationLine == null) {
        //             return "StationLine not found for the given line";
        //         }

                
        // בדיקת זמן
        for (Travel travel : travels) {
            // המרת זמן היציאה ל-LocalTime
            LocalTime departureLocalTime = travel.getDepature_time().toLocalTime();

            // הוספת הדיליי לפי סדר התחנה במסלול
            LocalTime arrivalLocalTime = departureLocalTime.plusMinutes(stationLine.getStation_order());

            // המרת הזמן המשוער חזרה ל-Time
            Time estimatedArrivalTime = Time.valueOf(arrivalLocalTime);

            // השוואה בין הזמן הנוכחי לזמן הגעה
            // if (currentTime.compareTo(estimatedArrivalTime) >= 0) {
            // arrivelTimes += " יגיע לתחנה: " + (currentTime - estimatedArrivalTime ) +
            // "\n";
            // }
            if (currentTime.compareTo(estimatedArrivalTime) <= 0) {
                long diffInMillis = estimatedArrivalTime.getTime() - currentTime.getTime();
                long diffMinutes = diffInMillis / (1000 * 60);
                arrivelTimes += " יגיע לתחנה בעוד: " + diffMinutes + " דקות\n";
            }

        }
        return arrivelTimes;
    }

    public String SearchByStation(Long stationNumber)
    {
        Station station = stationRepository.findById(stationNumber).orElse(null);
        if (station == null) {
            return "Station not found";
        }
        List<StationLine> lines = station.getStationLines();

        String arrivalTimes="תחנה: "+station.getName();
        for(StationLine line : lines) {
            arrivalTimes+="\nקו: "+line.getLine().getNumber()+SearchByStation(stationNumber,line.getLine().getNumber());

    }
    return arrivalTimes;
}
    public Long add(StationDTO stationDTO) {
        Station station = stationConverter.toStation(stationDTO);
        if (!stationRepository.exists(Example.of(station))) {

            return stationRepository.save(station).getId();
        }
        return null;
    }

    public List<StationDTO> getAll() {
        return stationRepository.findAll().stream()
                .map(stationConverter::toStationDTO)
                .collect((Collectors.toList()));
    }
}
