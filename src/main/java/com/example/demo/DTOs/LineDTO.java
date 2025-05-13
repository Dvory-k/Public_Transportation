package com.example.demo.DTOs;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineDTO {

    private long id;
    private String number;
    private String source;
    private String destination;

    //לכל קו יש הרבה נסיעות

    private List<Long> travelsIDs;
 
     private List<Long> stationsLinesIDs;

}
