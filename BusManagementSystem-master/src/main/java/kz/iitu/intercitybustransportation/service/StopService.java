package kz.iitu.intercitybustransportation.service;

import kz.iitu.intercitybustransportation.dto.StopDTO;

import java.util.List;

public interface StopService {
    StopDTO getStop(Long id);
    List<StopDTO> getAllStops();
    StopDTO createStop(StopDTO stopDto);
    StopDTO updateStop(Long id, StopDTO stopDto);
    void deleteStop(Long id);
}

