package kz.iitu.intercitybustransportation.service;

import kz.iitu.intercitybustransportation.dto.BusDTO;

import java.util.List;

public interface BusService {
    BusDTO getBus(Long id);
    List<BusDTO> getAllBuses();
    BusDTO createBus(BusDTO busDto);
    BusDTO updateBus(Long id, BusDTO busDto);
    void deleteBus(Long id);
}