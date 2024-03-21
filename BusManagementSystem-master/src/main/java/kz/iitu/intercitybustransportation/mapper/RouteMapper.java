package kz.iitu.intercitybustransportation.mapper;

import kz.iitu.intercitybustransportation.dto.RouteDTO;
import kz.iitu.intercitybustransportation.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RouteMapper {

    private final StopMapper stopMapper;

    @Autowired
    public RouteMapper(StopMapper stopMapper) {
        this.stopMapper = stopMapper;
    }

    public RouteDTO toDto(Route route) {
        RouteDTO routeDto = new RouteDTO();
        routeDto.setId(route.getId());
        routeDto.setOrigin(route.getOrigin());
        routeDto.setDestination(route.getDestination());
        routeDto.setDuration(route.getDuration());
        routeDto.setStops(route.getStops().stream()
                .map(stopMapper::toDto)
                .collect(Collectors.toList()));
        return routeDto;
    }

    public Route toEntity(RouteDTO routeDto) {
        Route route = new Route();
        route.setId(routeDto.getId());
        route.setOrigin(routeDto.getOrigin());
        route.setDestination(routeDto.getDestination());
        route.setDuration(routeDto.getDuration());
        route.setStops(routeDto.getStops().stream()
                .map(stopMapper::toEntity)
                .collect(Collectors.toList()));
        return route;
    }
}
