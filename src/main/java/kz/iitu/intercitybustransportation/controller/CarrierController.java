package kz.iitu.intercitybustransportation.controller;

import kz.iitu.intercitybustransportation.dto.CarrierDTO;
import kz.iitu.intercitybustransportation.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carriers")
public class CarrierController {

    private final CarrierService carrierService;

    @Autowired
    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrierDTO> getCarrier(@PathVariable Long id) {
        CarrierDTO carrierDto = carrierService.getCarrier(id);
        return new ResponseEntity<>(carrierDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CarrierDTO>> getAllCarriers() {
        List<CarrierDTO> carrierDtos = carrierService.getAllCarriers();
        return new ResponseEntity<>(carrierDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarrierDTO> createCarrier(@RequestBody CarrierDTO carrierDto) {
        CarrierDTO createdCarrierDto = carrierService.createCarrier(carrierDto);
        return new ResponseEntity<>(createdCarrierDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrierDTO> updateCarrier(@PathVariable Long id, @RequestBody CarrierDTO carrierDto) {
        CarrierDTO updatedCarrierDto = carrierService.updateCarrier(id, carrierDto);
        return new ResponseEntity<>(updatedCarrierDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrier(@PathVariable Long id) {
        carrierService.deleteCarrier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
