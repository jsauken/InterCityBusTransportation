package kz.iitu.intercitybustransportation.mapper;


import kz.iitu.intercitybustransportation.dto.StopDTO;
import kz.iitu.intercitybustransportation.model.Stop;
import org.springframework.stereotype.Component;

// Mapper
@Component
public class StopMapper {

    public StopDTO toDto(Stop stop) {
        StopDTO stopDto = new StopDTO();
        stopDto.setId(stop.getId());
        stopDto.setLocation(stop.getLocation());
        stopDto.setArrivalTime(stop.getArrivalTime());
        stopDto.setDepartureTime(stop.getDepartureTime());
        return stopDto;
    }

    public Stop toEntity(StopDTO stopDto) {
        Stop stop = new Stop();
        stop.setId(stopDto.getId());
        stop.setLocation(stopDto.getLocation());
        stop.setArrivalTime(stopDto.getArrivalTime());
        stop.setDepartureTime(stopDto.getDepartureTime());
        return stop;
    }
}