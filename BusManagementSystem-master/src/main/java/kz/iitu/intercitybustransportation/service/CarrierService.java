package kz.iitu.intercitybustransportation.service;

import kz.iitu.intercitybustransportation.dto.CarrierDTO;

import java.util.List;

public interface CarrierService {
    CarrierDTO getCarrier(Long id);
    List<CarrierDTO> getAllCarriers();
    CarrierDTO createCarrier(CarrierDTO carrierDto);
    CarrierDTO updateCarrier(Long id, CarrierDTO carrierDto);
    void deleteCarrier(Long id);
}
