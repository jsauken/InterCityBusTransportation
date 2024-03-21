package kz.iitu.intercitybustransportation.mapper;


import kz.iitu.intercitybustransportation.dto.PassengerDTO;
import kz.iitu.intercitybustransportation.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class PassengerMapper {

    private final UserMapper userMapper;

    @Autowired
    public PassengerMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public PassengerDTO toDto(Passenger passenger) {
        PassengerDTO passengerDto = new PassengerDTO();
        passengerDto.setId(passenger.getId());
        passengerDto.setFirstName(passenger.getFirstName());
        passengerDto.setLastName(passenger.getLastName());
        passengerDto.setEmail(passenger.getEmail());
        passengerDto.setBirthOfDate(passenger.getBirthOfDate());
        passengerDto.setUser(userMapper.toDto(passenger.getUser()));
        // map Ticket to TicketDto
        return passengerDto;
    }

    public Passenger toEntity(PassengerDTO passengerDto) {
        Passenger passenger = new Passenger();
        passenger.setId(passengerDto.getId());
        passenger.setFirstName(passengerDto.getFirstName());
        passenger.setLastName(passengerDto.getLastName());
        passenger.setEmail(passengerDto.getEmail());
        passenger.setBirthOfDate(passengerDto.getBirthOfDate());
        passenger.setUser(userMapper.toEntity(passengerDto.getUser()));
        // map TicketDto to Ticket
        return passenger;
    }
}
