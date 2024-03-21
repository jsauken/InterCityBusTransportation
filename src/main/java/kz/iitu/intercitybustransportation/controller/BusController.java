package kz.iitu.intercitybustransportation.controller;

import kz.iitu.intercitybustransportation.dto.BusDTO;
import kz.iitu.intercitybustransportation.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/buses")
public class BusController {

    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusDTO> getBus(@PathVariable Long id) {
        BusDTO busDto = busService.getBus(id);
        return new ResponseEntity<>(busDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BusDTO>> getAllBuses() {
        List<BusDTO> busDtos = busService.getAllBuses();
        return new ResponseEntity<>(busDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BusDTO> createBus(@RequestBody BusDTO busDto) {
        BusDTO createdBusDto = busService.createBus(busDto);
        return new ResponseEntity<>(createdBusDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusDTO> updateBus(@PathVariable Long id, @RequestBody BusDTO busDto) {
        BusDTO updatedBusDto = busService.updateBus(id, busDto);
        return new ResponseEntity<>(updatedBusDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
