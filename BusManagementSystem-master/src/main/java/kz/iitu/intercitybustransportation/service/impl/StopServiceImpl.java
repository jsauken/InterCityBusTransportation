package kz.iitu.intercitybustransportation.service.impl;

import kz.iitu.intercitybustransportation.dto.StopDTO;
import kz.iitu.intercitybustransportation.exceptions.ResourceNotFoundException;
import kz.iitu.intercitybustransportation.mapper.StopMapper;
import kz.iitu.intercitybustransportation.model.Stop;
import kz.iitu.intercitybustransportation.repository.StopRepository;
import kz.iitu.intercitybustransportation.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StopServiceImpl implements StopService {

    private final StopRepository stopRepository;
    private final StopMapper stopMapper;

    @Autowired
    public StopServiceImpl(StopRepository stopRepository, StopMapper stopMapper) {
        this.stopRepository = stopRepository;
        this.stopMapper = stopMapper;
    }

    @Override
    public StopDTO getStop(Long id) {
        return stopRepository.findById(id)
                .map(stopMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Stop not found with id " + id));
    }

    @Override
    public List<StopDTO> getAllStops() {
        return stopRepository.findAll().stream()
                .map(stopMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StopDTO createStop(StopDTO stopDto) {
        Stop stop = stopMapper.toEntity(stopDto);
        Stop savedStop = stopRepository.save(stop);
        return stopMapper.toDto(savedStop);
    }

    @Override
    public StopDTO updateStop(Long id, StopDTO stopDto) {
        return stopRepository.findById(id)
                .map(stop -> {
                    // Update the fields of the stop as per your requirements
                    Stop updatedStop = stopRepository.save(stop);
                    return stopMapper.toDto(updatedStop);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Stop not found with id " + id));
    }

    @Override
    public void deleteStop(Long id) {
        stopRepository.findById(id)
                .ifPresentOrElse(stopRepository::delete, () -> {
                    throw new ResourceNotFoundException("Stop not found with id " + id);
                });
    }
}
