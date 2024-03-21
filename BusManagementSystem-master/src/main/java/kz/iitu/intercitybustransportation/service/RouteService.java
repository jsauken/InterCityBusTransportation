package kz.iitu.intercitybustransportation.service;

import kz.iitu.intercitybustransportation.dto.RouteDTO;

import java.util.List;

public interface RouteService {
    RouteDTO getRoute(Long id);
    List<RouteDTO> getAllRoutes();
    RouteDTO createRoute(RouteDTO routeDto);
    RouteDTO updateRoute(Long id, RouteDTO routeDto);
    void deleteRoute(Long id);
}

