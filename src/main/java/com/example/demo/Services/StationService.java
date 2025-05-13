package com.example.demo.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import com.example.demo.Converters.StationConverter;

import com.example.demo.DTOs.StationDTO;

import com.example.demo.Models.Station;

import com.example.demo.Repositories.StationRepository;


@Service
public class StationService {

    @Autowired
    private  StationRepository stationRepository;
      @Autowired
    private  StationConverter stationConverter;

    public Long add(StationDTO stationDTO) {
    Station station = stationConverter.toStation(stationDTO);
        if (!stationRepository.exists(Example.of(station))) {

            return stationRepository.save(station).getId();
        }
        return null;    }

    public List<StationDTO> getAll()
    {
    return stationRepository.findAll().stream()
    .map(stationConverter::toStationDTO)
    .collect((Collectors.toList()));
    }
}
