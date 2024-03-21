package kz.iitu.intercitybustransportation.controller;

import kz.iitu.intercitybustransportation.dto.PassengerDTO;
import kz.iitu.intercitybustransportation.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/{id}")
    public PassengerDTO getPassenger(@PathVariable Long id) {
        return passengerService.getPassenger(id);
    }

    @GetMapping
    public List<PassengerDTO> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @PostMapping
    public PassengerDTO createPassenger(@RequestBody PassengerDTO passengerDto) {
        return passengerService.createPassenger(passengerDto);
    }

    @PutMapping("/{id}")
    public PassengerDTO updatePassenger(@PathVariable Long id, @RequestBody PassengerDTO passengerDto) {
        return passengerService.updatePassenger(id, passengerDto);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }
}
