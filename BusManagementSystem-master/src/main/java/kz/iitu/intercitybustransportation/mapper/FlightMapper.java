package kz.iitu.intercitybustransportation.mapper;


import kz.iitu.intercitybustransportation.dto.FlightDTO;
import kz.iitu.intercitybustransportation.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Mapper
@Component
public class FlightMapper {

    private final BusMapper busMapper;
    private final RouteMapper routeMapper;

    @Autowired
    public FlightMapper(BusMapper busMapper, RouteMapper routeMapper) {
        this.busMapper = busMapper;
        this.routeMapper = routeMapper;
    }

    public FlightDTO toDto(Flight flight) {
        FlightDTO flightDto = new FlightDTO();
        flightDto.setId(flight.getId());
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setArrivalTime(flight.getArrivalTime());
        flightDto.setRoute(routeMapper.toDto(flight.getRoute()));
        flightDto.setBus(busMapper.toDto(flight.getBus()));
        flightDto.setPrice(flight.getPrice());
        return flightDto;
    }

    public Flight toEntity(FlightDTO flightDto) {
        Flight flight = new Flight();
        flight.setId(flightDto.getId());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setRoute(routeMapper.toEntity(flightDto.getRoute()));
        flight.setBus(busMapper.toEntity(flightDto.getBus()));
        flight.setPrice(flightDto.getPrice());
        return flight;
    }
}
