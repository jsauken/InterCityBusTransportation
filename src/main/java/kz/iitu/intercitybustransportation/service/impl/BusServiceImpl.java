package kz.iitu.intercitybustransportation.service.impl;

import kz.iitu.intercitybustransportation.dto.BusDTO;
import kz.iitu.intercitybustransportation.exceptions.ResourceNotFoundException;
import kz.iitu.intercitybustransportation.mapper.BusMapper;
import kz.iitu.intercitybustransportation.model.Bus;
import kz.iitu.intercitybustransportation.repository.BusRepository;
import kz.iitu.intercitybustransportation.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;
    private final BusMapper busMapper;

    @Autowired
    public BusServiceImpl(BusRepository busRepository, BusMapper busMapper) {
        this.busRepository = busRepository;
        this.busMapper = busMapper;
    }

    @Override
    public BusDTO getBus(Long id) {
        return busRepository.findById(id)
                .map(busMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id " + id));
    }

    @Override
    public List<BusDTO> getAllBuses() {
        return busRepository.findAll().stream()
                .map(busMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BusDTO createBus(BusDTO busDto) {
        Bus bus = busMapper.toEntity(busDto);
        Bus savedBus = busRepository.save(bus);
        return busMapper.toDto(savedBus);
    }

    @Override
    public BusDTO updateBus(Long id, BusDTO busDto) {
        return busRepository.findById(id)
                .map(bus -> {
                    bus.setBrand(busDto.getBrand());
                    bus.setCapacity(busDto.getCapacity());
                    bus.setRegistrationNumber(busDto.getRegistrationNumber());
                    bus.setAmenities(busDto.getAmenities());
                    bus.setCarrier(busDto.getCarrier());
                    Bus updatedBus = busRepository.save(bus);
                    return busMapper.toDto(updatedBus);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id " + id));
    }

    @Override
    public void deleteBus(Long id) {
        busRepository.findById(id)
                .ifPresentOrElse(busRepository::delete, () -> {
                    throw new ResourceNotFoundException("Bus not found with id " + id);
                });
    }
}

