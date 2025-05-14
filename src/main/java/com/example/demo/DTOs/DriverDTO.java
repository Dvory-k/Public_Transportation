package com.example.demo.DTOs;

import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO{

    private long id;
    private String name;
    private String phone;
    private List<Long> travelsIDs;
}
