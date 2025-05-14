package com.example.demo.DTOs;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationLineDTO {
 
    private long id;

    private String lineNumber;

    private String stationName;

    private int station_order;
}
