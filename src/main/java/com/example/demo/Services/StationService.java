package com.example.demo.Services;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.demo.Converters.StationConverter;
import com.example.demo.DTOs.StationDTO;
import com.example.demo.Models.Line;
import com.example.demo.Models.Station;
import com.example.demo.Models.StationLine;
import com.example.demo.Models.Travel;
import com.example.demo.Repositories.LineRepository;
import com.example.demo.Repositories.StationRepository;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private StationConverter stationConverter;
    @Autowired
    private LineRepository lineRepository;

    // חיפוש לפי תחנה וקו
    public String SearchByStation(Long stationNumber, String lineNumber) {
        // חיפוש תחנה
        Station station = stationRepository.findById(stationNumber).orElse(null);
        if (station == null) {
            return "לא נמצא מידע על התחנה או שהנתון שהוקש שגוי";
        }
        // חיפוש קו
        Line line = lineRepository.findByNumber(lineNumber);
        if (line == null) {
            return "לא נמצא מידע על הקו או שאחד הנתונים שהוקש שגוי";
        }
        // שליפת כל נסיעות הקו
        List<Travel> travels = line.getTravels();
        if (travels == null || travels.size() == 0)
            return "לאנמצא מידע בטווח הזמן הקרוב על הקו המבוקש";

        // זמן נוכחי
        Time currentTime = Time.valueOf(LocalTime.now());
        // מיון לפי זמן יציאה
        travels.sort(Comparator.comparingLong(
                travel -> Math.abs(currentTime.getTime() - travel.getDepature_time().getTime())));
        // החזרת זמני הגעה
        String arrivelTimes = "קו " + line.getNumber() + " - יגיע לתחנה " + station.getName() + "\n";

        // שליפת ה stationLine
        StationLine stationLine = station.getStationLines().stream()
                .filter(stationL -> stationL.getLine().getNumber().equals(lineNumber.toString())).findFirst()
                .orElse(null);
        if (stationLine == null) {
            return "לא נמצא מידע בטווח הזמן הקרוב או שאחד הנתונים שהוקשו שגוי";
        }

        // בדיקת זמן
        for (Travel travel : travels) {
            // המרת זמן היציאה ל-LocalTime
            LocalTime departureLocalTime = travel.getDepature_time().toLocalTime();

            // הוספת הדיליי לפי סדר התחנה במסלול
            LocalTime arrivalLocalTime = departureLocalTime.plusMinutes(stationLine.getStation_order());

            // המרת הזמן המשוער חזרה ל-Time
            Time estimatedArrivalTime = Time.valueOf(arrivalLocalTime);

            if (currentTime.compareTo(estimatedArrivalTime) <= 0) {
                long diffInMillis = estimatedArrivalTime.getTime() - currentTime.getTime();
                long diffMinutes = diffInMillis / (1000 * 60);
                arrivelTimes += " יגיע לתחנה בעוד: " + diffMinutes + " דקות\n";
            }

        }
        return arrivelTimes;
    }

    // חיפוש כל הקווים בתחנה המבוקשת
    public String SearchByStation(Long stationNumber) {
        // מציאת התחנה
        Station station = stationRepository.findById(stationNumber).orElse(null);
        if (station == null) {
            return "לא נמצא מידע על התחנה המבוקשת";
        }

        List<StationLine> lines = station.getStationLines();
        if (lines == null || lines.size()==0) {
            return "לא נמצא מידע בטווח הזמן הקרוב";
        }

        String arrivalTimes = "תחנה: " + station.getName();
        for (StationLine line : lines) {
            arrivalTimes += "\nקו: " + line.getLine().getNumber()
                    + SearchByStation(stationNumber, line.getLine().getNumber());//שליחה לפונקציה הקודמת

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
