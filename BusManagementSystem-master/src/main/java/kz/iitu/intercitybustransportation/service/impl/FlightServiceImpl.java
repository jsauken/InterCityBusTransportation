package kz.iitu.intercitybustransportation.service.impl;

import kz.iitu.intercitybustransportation.dto.FlightDTO;
import kz.iitu.intercitybustransportation.exceptions.ResourceNotFoundException;
import kz.iitu.intercitybustransportation.mapper.FlightMapper;
import kz.iitu.intercitybustransportation.model.Flight;
import kz.iitu.intercitybustransportation.repository.FlightRepository;
import kz.iitu.intercitybustransportation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public FlightDTO getFlight(Long id) {
        return flightRepository.findById(id)
                .map(flightMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id " + id));
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FlightDTO createFlight(FlightDTO flightDto) {
        Flight flight = flightMapper.toEntity(flightDto);
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.toDto(savedFlight);
    }

    @Override
    public FlightDTO updateFlight(Long id, FlightDTO flightDto) {
        return flightRepository.findById(id)
                .map(flight -> {
                    // Update the fields of the flight as per your requirements
                    Flight updatedFlight = flightRepository.save(flight);
                    return flightMapper.toDto(updatedFlight);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id " + id));
    }



    @Override
    public void deleteFlight(Long id) {
        flightRepository.findById(id)
                .ifPresentOrElse(flightRepository::delete, () -> {
                    throw new ResourceNotFoundException("Flight not found with id " + id);
                });
    }
}
