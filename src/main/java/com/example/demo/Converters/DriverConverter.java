package com.example.demo.Converters;


import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.DTOs.DriverDTO;
import com.example.demo.Models.Driver;
import com.example.demo.Models.Travel;

public class DriverConverter {
    
     public  DriverDTO toDriverDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setName(driver.getName());
        driverDTO.setPhone(driver.getPhone());
        driverDTO.setTravelsIDs(driver.getTravels().stream().map(Travel::getId).collect(Collectors.toList()));
        return driverDTO;
    }


    public  Driver toDriver(DriverDTO driverDTO) {
    Driver driver = new Driver();
    driver.setId(driverDTO.getId());
    driver.setName(driverDTO.getName());
    driver.setPhone(driverDTO.getPhone());
    
    // if (driverDTO.getTravelsIDs() != null) {
    //     driver.setTravels(driverDTO.getTravelsIDs().stream()
    //         .map(id -> travelRepository.findById(id).orElse(null))
    //         .collect(Collectors.toList()));
    // } else {
    //     driver.setTravels(new ArrayList<>()); // או טיפול אחר במקרה של null
    // }
    
    return driver;
}

    
//המרת רשימת driverDTO לרשימת driver
    public  List<Driver> toDriverList(List<DriverDTO> driverDTOs) {
        return driverDTOs.stream().map(driver -> toDriver(driver)).collect(Collectors.toList());
    }

    //המרת רשימת driver לרשימת driverDTO
    public  List<DriverDTO> toDriverDTOList(List<Driver> drivers) {
        return drivers.stream().map(driver -> toDriverDTO(driver)).collect(Collectors.toList());
    }

   
   

   


}
