// package com.example.demo.Converters;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import com.example.demo.DTOs.TravelDTO;
// import com.example.demo.Models.Travel;
// import com.example.demo.Repositories.BusRepository;
// import com.example.demo.Repositories.DriverRepository;
// import com.example.demo.Repositories.LineRepository;

// @Component
// public class TravelConverter {

//      private final BusRepository busRepository;
//       private final LineRepository lineRepository;
//           private final DriverRepository driverRepository;

//     @Autowired
//     public TravelConverter(BusRepository busRepository) {
//         this.busRepository = busRepository;
//     }
    
   


  
// //המרת travel לtravelDto
// public static TravelDTO toTravelDTO(Travel travel) {
//     TravelDTO travelDTO = new TravelDTO();
//     travelDTO.setId(travel.getId());
//     travelDTO.setDepartureTime(travel.getDepature_time());
//     travelDTO.setBus_id(travel.getBus().getId());
//     travelDTO.setLine_id(travel.getLine().getId());
//     travelDTO.setDriver_id(travel.getDriver().getId());
//     return travelDTO;
// }

//     //המרת travelDto לtravel
//     public static Travel toTravel(TravelDTO travelDTO) {
//         Travel travel = new Travel();
//         travel.setId(travelDTO.getId());
//         travel.setDepature_time(travelDTO.getDepartureTime());
//         travel.setBus(busRepository.findById(travelDTO.getBus_id()).orElse(null));
//         travel.setLine(lineRepository.findById(travelDTO.getLine_id()).orElse(null));
//         travel.setDriver(driverRepository.findById(travelDTO.getDriver_id()).orElse(null));
//         return travel;
//     }
//     //המרת ��שי��ת travel לרשי��ת travelDto
//     public static List<TravelDTO> toTravelDTOList(List<Travel> travels) {
//         return travels.stream().map(travel -> TravelConverter.toTravelDTO(travel)).collect(Collectors.toList());
//     }
//     //המרת ��שי��ת travelDto לרשי��ת travel
//     public static List<Travel> toTravelList(List<TravelDTO> travelDTOs) {
//         return travelDTOs.stream().map(travel -> TravelConverter.toTravel(travel)).collect(Collectors.toList());
//     }


//     }
package com.example.demo.Converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DTOs.TravelDTO;
import com.example.demo.Models.Travel;
import com.example.demo.Repositories.BusRepository;
import com.example.demo.Repositories.DriverRepository;
import com.example.demo.Repositories.LineRepository;


public class TravelConverter {

    // private final BusRepository busRepository;
    // private final LineRepository lineRepository;
    // private final DriverRepository driverRepository;

    // @Autowired
    // public TravelConverter(BusRepository busRepository, LineRepository lineRepository, DriverRepository driverRepository) {
    //     this.busRepository = busRepository;
    //     this.lineRepository = lineRepository;
    //     this.driverRepository = driverRepository;
    // }

    @Autowired
    private BusRepository busRepository;
     @Autowired
    private LineRepository lineRepository;
     @Autowired
    private DriverRepository driverRepository;

    // המרת Travel ל-TravelDTO
    public TravelDTO toTravelDTO(Travel travel) {
        TravelDTO travelDTO = new TravelDTO();
        travelDTO.setId(travel.getId());
        travelDTO.setDepartureTime(travel.getDepature_time());
        travelDTO.setBus_id(travel.getBus().getId());
        travelDTO.setLine_id(travel.getLine().getId());
        travelDTO.setDriver_id(travel.getDriver().getId());
        return travelDTO;
    }

    // המרת TravelDTO ל-Travel
    public Travel toTravel(TravelDTO travelDTO) {
        Travel travel = new Travel();
        travel.setId(travelDTO.getId());
        travel.setDepature_time(travelDTO.getDepartureTime());
        travel.setBus(busRepository.findById(travelDTO.getBus_id()).orElse(null));
        travel.setLine(lineRepository.findById(travelDTO.getLine_id()).orElse(null));
        travel.setDriver(driverRepository.findById(travelDTO.getDriver_id()).orElse(null));
        return travel;
    }

    // רשימת Travel -> רשימת TravelDTO
    public List<TravelDTO> toTravelDTOList(List<Travel> travels) {
        return travels.stream().map(this::toTravelDTO).collect(Collectors.toList());
    }

    // רשימת TravelDTO -> רשימת Travel
    public List<Travel> toTravelList(List<TravelDTO> travelDTOs) {
        return travelDTOs.stream().map(this::toTravel).collect(Collectors.toList());
    }
}
