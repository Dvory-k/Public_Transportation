package com.example.demo.DTOs;

import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationDTO {

    private long id;
    private String name;

    //הקווים שעוברים בתחנה הנוכחית
     private List<String> stationsLinesNumbers;
    
}
