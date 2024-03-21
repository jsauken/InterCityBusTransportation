package kz.iitu.intercitybustransportation.service;

import kz.iitu.intercitybustransportation.dto.FlightDTO;

import java.util.List;

public interface FlightService {
    FlightDTO getFlight(Long id);
    List<FlightDTO> getAllFlights();
    FlightDTO createFlight(FlightDTO flightDto);
    FlightDTO updateFlight(Long id, FlightDTO flightDto);
    void deleteFlight(Long id);
}
