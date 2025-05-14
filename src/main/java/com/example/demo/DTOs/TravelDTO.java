package com.example.demo.DTOs;

import java.sql.Time;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelDTO {

    private long id;

    //לכל נסיעה אוטובוס אחד

    private Long bus_id;

    //לכל נסיעה נהג אחד

    private Long driver_id;

    //לכל נסיעה קו אחד

    private Long line_id;

    //זמן יציאה
    private Time departureTime;

}
