package com.example.demo.Services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.demo.Converters.BusConverter;
import com.example.demo.DTOs.BusDTO;
import com.example.demo.Models.Bus;
import com.example.demo.Repositories.BusRepository;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;
    @Autowired
    private BusConverter busConverter;

    public Long addBus(BusDTO busDto) {
        Bus bus = busConverter.toBus(busDto);
        if (!busRepository.exists(Example.of(bus))) {

            return busRepository.save(bus).getId();
        }
        return null;
    }

    public List<BusDTO> getAll() {
        return busRepository.findAll().stream()
                .map(busConverter::toBusDTO)
                .collect((Collectors.toList()));
    }

}