package kz.iitu.intercitybustransportation.service.impl;

import kz.iitu.intercitybustransportation.dto.PassengerDTO;
import kz.iitu.intercitybustransportation.exceptions.ResourceNotFoundException;
import kz.iitu.intercitybustransportation.mapper.PassengerMapper;
import kz.iitu.intercitybustransportation.model.Passenger;
import kz.iitu.intercitybustransportation.repository.PassengerRepository;
import kz.iitu.intercitybustransportation.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public PassengerDTO getPassenger(Long id) {
        return passengerRepository.findById(id)
                .map(passengerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id " + id));
    }

    @Override
    public List<PassengerDTO> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(passengerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PassengerDTO createPassenger(PassengerDTO passengerDto) {
        Passenger passenger = passengerMapper.toEntity(passengerDto);
        Passenger savedPassenger = passengerRepository.save(passenger);
        return passengerMapper.toDto(savedPassenger);
    }

    @Override
    public PassengerDTO updatePassenger(Long id, PassengerDTO passengerDto) {
        return passengerRepository.findById(id)
                .map(passenger -> {
                    // Update the fields of the passenger as per your requirements
                    Passenger updatedPassenger = passengerRepository.save(passenger);
                    return passengerMapper.toDto(updatedPassenger);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id " + id));
    }

    @Override
    public void deletePassenger(Long id) {
        passengerRepository.findById(id)
                .ifPresentOrElse(passengerRepository::delete, () -> {
                    throw new ResourceNotFoundException("Passenger not found with id " + id);
                });
    }
}
