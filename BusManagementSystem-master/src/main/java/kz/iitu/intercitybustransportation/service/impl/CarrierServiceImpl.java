package kz.iitu.intercitybustransportation.service.impl;

import kz.iitu.intercitybustransportation.dto.CarrierDTO;
import kz.iitu.intercitybustransportation.exceptions.ResourceNotFoundException;
import kz.iitu.intercitybustransportation.mapper.CarrierMapper;
import kz.iitu.intercitybustransportation.model.Carrier;
import kz.iitu.intercitybustransportation.repository.CarrierRepository;
import kz.iitu.intercitybustransportation.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Service Implementation
@Service
public class CarrierServiceImpl implements CarrierService {
    @Autowired
    private CarrierRepository carrierRepository;
    @Autowired
    private CarrierMapper carrierMapper;

    @Override
    public CarrierDTO getCarrier(Long id) {
        return carrierRepository.findById(id)
                .map(carrierMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Carrier not found with id " + id));
    }
    @Override
    public List<CarrierDTO> getAllCarriers() {
        return carrierRepository.findAll().stream()
                .map(carrierMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarrierDTO createCarrier(CarrierDTO carrierDto) {
        Carrier carrier = carrierMapper.toEntity(carrierDto);
        Carrier savedCarrier = carrierRepository.save(carrier);
        return carrierMapper.toDto(savedCarrier);
    }

    @Override
    public CarrierDTO updateCarrier(Long id, CarrierDTO carrierDto) {
        return carrierRepository.findById(id)
                .map(carrier -> {
                    carrier.setBusCompanyName(carrierDto.getBusCompanyName());
                    carrier.setPhoneNumber(carrierDto.getPhoneNumber());
                    carrier.setEmail(carrierDto.getEmail());
                    carrier.setWebsite(carrierDto.getWebsite());
                    Carrier updatedCarrier = carrierRepository.save(carrier);
                    return carrierMapper.toDto(updatedCarrier);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Carrier not found with id " + id));
    }

    @Override
    public void deleteCarrier(Long id) {
        carrierRepository.findById(id)
                .ifPresentOrElse(carrierRepository::delete, () -> {
                    throw new ResourceNotFoundException("Carrier not found with id " + id);
                });
    }
    //p
}