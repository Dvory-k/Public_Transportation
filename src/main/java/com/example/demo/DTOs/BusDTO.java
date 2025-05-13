package com.example.demo.DTOs;

import java.util.List;

// import java.util.List;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusDTO {

    private long id;
    private String licensePlate;
    private int seats;
    private List<Long> travelsIDs;

}


