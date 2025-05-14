package com.example.demo.Services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.demo.Converters.DriverConverter;
import com.example.demo.DTOs.DriverDTO;
import com.example.demo.Models.Driver;
import com.example.demo.Repositories.DriverRepository;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverConverter driverConverter;

    public Long addDriver(DriverDTO driverDTO) {
        Driver driver = driverConverter.toDriver(driverDTO);
        if (!driverRepository.exists(Example.of(driver))) {

            return driverRepository.save(driver).getId();
        }
        return null;
    }

    public List<DriverDTO> getAll() {
        return driverRepository.findAll().stream()
                .map(driverConverter::toDriverDTO)
                .collect((Collectors.toList()));
    }
}
