package kz.iitu.intercitybustransportation.service;

import kz.iitu.intercitybustransportation.dto.PassengerDTO;

import java.util.List;

public interface PassengerService {
    PassengerDTO getPassenger(Long id);
    List<PassengerDTO> getAllPassengers();
    PassengerDTO createPassenger(PassengerDTO passengerDto);
    PassengerDTO updatePassenger(Long id, PassengerDTO passengerDto);
    void deletePassenger(Long id);
}
