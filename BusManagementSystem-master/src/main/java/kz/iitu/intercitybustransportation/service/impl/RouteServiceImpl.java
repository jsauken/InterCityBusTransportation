package kz.iitu.intercitybustransportation.service.impl;

import kz.iitu.intercitybustransportation.dto.RouteDTO;
import kz.iitu.intercitybustransportation.exceptions.ResourceNotFoundException;
import kz.iitu.intercitybustransportation.mapper.RouteMapper;
import kz.iitu.intercitybustransportation.model.Route;
import kz.iitu.intercitybustransportation.repository.RouteRepository;
import kz.iitu.intercitybustransportation.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
    }

    @Override
    public RouteDTO getRoute(Long id) {
        return routeRepository.findById(id)
                .map(routeMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with id " + id));
    }

    @Override
    public List<RouteDTO> getAllRoutes() {
        return routeRepository.findAll().stream()
                .map(routeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RouteDTO createRoute(RouteDTO routeDto) {
        Route route = routeMapper.toEntity(routeDto);
        Route savedRoute = routeRepository.save(route);
        return routeMapper.toDto(savedRoute);
    }

    @Override
    public RouteDTO updateRoute(Long id, RouteDTO routeDto) {
        return routeRepository.findById(id)
                .map(route -> {
                    // Update the fields of the route as per your requirements
                    Route updatedRoute = routeRepository.save(route);
                    return routeMapper.toDto(updatedRoute);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with id " + id));
    }

    @Override
    public void deleteRoute(Long id) {
        routeRepository.findById(id)
                .ifPresentOrElse(routeRepository::delete, () -> {
                    throw new ResourceNotFoundException("Route not found with id " + id);
                });
    }
}
