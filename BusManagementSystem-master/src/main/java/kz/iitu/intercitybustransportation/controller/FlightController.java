package kz.iitu.intercitybustransportation.controller;

import kz.iitu.intercitybustransportation.dto.FlightDTO;
import kz.iitu.intercitybustransportation.dto.PageRequestDto;
import kz.iitu.intercitybustransportation.model.Flight;
import kz.iitu.intercitybustransportation.repository.FlightRepository;
import kz.iitu.intercitybustransportation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;
    private final FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightService flightService, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
    }

    @GetMapping("/{id}")
    public FlightDTO getFlight(@PathVariable Long id) {
        return flightService.getFlight(id);
    }

    @GetMapping
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping
    public FlightDTO createFlight(@RequestBody FlightDTO flightDto) {
        return flightService.createFlight(flightDto);
    }

    @PutMapping("/{id}")
    public FlightDTO updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDto) {
        return flightService.updateFlight(id, flightDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    @PostMapping("findByDate/{date}")
    public Page<Flight> getAllStudentUsingPagination(@RequestBody PageRequestDto dto,  @PathVariable(value = "date") String date )
    {
        Date newDate = convertStringToDate(date);
        Pageable pageable = new PageRequestDto().getPageable(dto);
        Page<Flight> flightPage = flightRepository.findByDepartureTime(newDate, pageable);
        return  flightPage;
    }


    @PostMapping("/{origin}/{destination}")
    public Page<Flight> getAllStudentUsingPaginationNative(@RequestBody PageRequestDto dto,
                                                            @PathVariable(value = "origin") String origin,  @PathVariable(value = "destination") String destination)
    {
        Pageable pageable = new PageRequestDto().getPageable(dto);
        return flightRepository.findAllByRoute(origin,destination, pageable);
    }


    public Date convertStringToDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = formatter.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
