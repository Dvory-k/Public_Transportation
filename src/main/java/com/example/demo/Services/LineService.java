package com.example.demo.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import com.example.demo.Converters.LineConverter;

import com.example.demo.DTOs.LineDTO;

import com.example.demo.Models.Line;

import com.example.demo.Repositories.LineRepository;


@Service
public class LineService {

    @Autowired
    private  LineRepository lineRepository;
        @Autowired
    private  LineConverter lineConverter;

    public Long addBus(LineDTO lineDto) {
    Line line = lineConverter.toLine(lineDto);
        if (!lineRepository.exists(Example.of(line))) {

            return lineRepository.save(line).getId();
        }
        return null;    }

    public List<LineDTO> getAll()
    {
    return lineRepository.findAll().stream()
    .map(lineConverter::toLineDTO)
    .collect((Collectors.toList()));
    }
}
