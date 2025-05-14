package com.example.demo.Converters;

import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.DTOs.BusDTO;
import com.example.demo.Models.Bus;

public class BusConverter {

    public BusDTO toBusDTO(Bus bus) {
        BusDTO busDTO = new BusDTO();
        busDTO.setId(bus.getId());
        busDTO.setLicensePlate(bus.getLicensePlate());
        busDTO.setSeats(bus.getSeats());
        busDTO.setTravelsTimes(bus.getTravels().stream()
                .map(travel -> travel.getDepature_time()).collect(Collectors.toList()));
        return busDTO;
    }

    // המרת busDTO לbus
    public Bus toBus(BusDTO busDTO) {
        Bus bus = new Bus();
        bus.setId(busDTO.getId());
        bus.setLicensePlate(busDTO.getLicensePlate());
        bus.setSeats(busDTO.getSeats());
        // List<Travel> travels = busDTO.getTravelsIDs().stream()
        // .map(id ->
        // travelRepository.findById(id).orElse(null)).collect(Collectors.toList());
        // bus.setTravels(travels);
        return bus;
    }

    // המרת רשימת busDTO לרשי��ת bus
    public List<BusDTO> toBusDTOList(List<Bus> buses) {
        return buses.stream()
                .map(bus -> toBusDTO(bus))
                .collect(Collectors.toList());
    }

    // המרת ��שי��ת bus לרשי��ת busDTO
    public List<Bus> toBusList(List<BusDTO> busDTOs) {
        return busDTOs.stream().map(bus -> toBus(bus)).collect(Collectors.toList());
    }

}
