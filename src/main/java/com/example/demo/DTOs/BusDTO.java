package com.example.demo.DTOs;

import java.sql.Time;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusDTO {

    private long id;
    private String licensePlate;
    private int seats;
    private List<Time> travelsTimes;

}


