package com.example.demo.Services;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.demo.Converters.LineConverter;
import com.example.demo.DTOs.LineDTO;
import com.example.demo.Models.Line;
import com.example.demo.Models.StationLine;
import com.example.demo.Models.Travel;
import com.example.demo.Repositories.LineRepository;

@Service
public class LineService {

    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private LineConverter lineConverter;

    public List<LocalTime> getAllTravels(String lineNumber)
    {
        // מחפש את הקו לפי מספר הקו
        Line line = lineRepository.findByNumber(lineNumber);
        if (line == null) {
            return null;
        }
        // מחפש את התחנות של הקו
        List<Travel> travels = line.getTravels().stream()
                .sorted(Comparator.comparing(Travel::getDepature_time))
                .collect(Collectors.toList());

        List<LocalTime> travelTimes = travels.stream()
                .map(travel -> travel.getDepature_time().toLocalTime())
                .collect(Collectors.toList());

        return travelTimes;
    }

    public List<LocalTime> getTravelsByHours(String lineNumber, LocalTime hour)
    {
        // מחפש את הקו לפי מספר הקו
        Line line = lineRepository.findByNumber(lineNumber);
        if (line == null) {
            return null;
        }
        //מחפשאת הנסיעות של הקו בשעה שלפני ואחרי השעה שהמשתמש הכניס

      List<Travel> travels = line.getTravels().stream()
            .filter(travel -> {
                LocalTime travelTime = travel.getDepature_time().toLocalTime();
                long minutesBetween = Math.abs(Duration.between(hour, travelTime).toMinutes());
                return minutesBetween < 60;
            })
            .sorted(Comparator.comparing(Travel::getDepature_time))
            .collect(Collectors.toList());

        List<LocalTime> travelTimes = travels.stream()
                .map(travel -> travel.getDepature_time().toLocalTime())
                .collect(Collectors.toList());

        return travelTimes;
    }
    
        
    
    public String getBusStations(String lineNumber) {
        // מחפש את הקו לפי מספר הקו
        Line line = lineRepository.findByNumber(lineNumber);
        if (line == null) {
            return "הקו לא קיים";
        }
        // מחפש את התחנות של הקו
        List<StationLine> stationsLines = line.getStationsLines().stream()
                .sorted(Comparator.comparingInt(StationLine::getStation_order))
                .collect(Collectors.toList());

        String string = "תחנות הקו " + line.getNumber() + ":\n";
        for (StationLine stationLine : stationsLines) {
            string+=stationLine.getStation().getName()+"\n";
        }
        return string;
    }

    public String getBusPlaces(String lineNumber) {
        // מחפש את הקו לפי מספר הקו
        Line line = lineRepository.findByNumber(lineNumber);
        if (line == null) {
            return "הקו לא קיים";
        }
        // מחפש את התחנות של הקו
        List<StationLine> stationsLines = line.getStationsLines().stream()
                .sorted(Comparator.comparingInt(StationLine::getStation_order))
                .collect(Collectors.toList());

        List<Travel> travels = line.getTravels().stream()
                .sorted(Comparator.comparing(Travel::getDepature_time))
                .collect(Collectors.toList());

        Time currentTime = Time.valueOf(LocalTime.now());
// System.out.println(line.toString()+"-----------------");
// System.out.println(line.getTravels().toString()+"-----------------");
        String string = "מיקומו של האוטובוס לאורך כל ציר הנסיעה:\n";
        for (Travel travel:travels) {
// System.out.println("--------------------"+i+"-----------------------------------");
long minutesSinceDeparture = (currentTime.getTime() - travel.getDepature_time().getTime()) / (1000 * 60);
            // Check if the travel time is greater than the current time
            if (minutesSinceDeparture >= 0 && minutesSinceDeparture < stationsLines.size()) {
                int index = (int)minutesSinceDeparture;
                string += "בקרבת התחנה: " +
                        stationsLines.get(index).getStation().getName() + "\n";
            }
           
        } 
        return string;
    }

    public Long addBus(LineDTO lineDto) {
        Line line = lineConverter.toLine(lineDto);
        if (!lineRepository.exists(Example.of(line))) {

            return lineRepository.save(line).getId();
        }
        return null;
    }

    public List<LineDTO> getAll() {
        return lineRepository.findAll().stream()
                .map(lineConverter::toLineDTO)
                .collect((Collectors.toList()));
    }
}
