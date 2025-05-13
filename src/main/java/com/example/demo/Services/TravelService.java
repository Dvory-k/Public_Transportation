package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.Converters.TravelConverter;
import com.example.demo.DTOs.TravelDTO;
import com.example.demo.Models.Travel;
import com.example.demo.Repositories.TravelRepository;

@Service
public class TravelService {


    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private TravelConverter travelConverter;

    // פונקציה להוספת נסיעה
    public Long add(TravelDTO travelDTO) {
        Travel travel = travelConverter.toTravel(travelDTO);
        if (!travelRepository.exists(Example.of(travel))) {
            return travelRepository.save(travel).getId();
        }
        return null;
    }
}
