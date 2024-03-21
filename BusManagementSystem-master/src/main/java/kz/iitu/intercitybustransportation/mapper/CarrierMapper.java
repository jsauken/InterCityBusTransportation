package kz.iitu.intercitybustransportation.mapper;

import kz.iitu.intercitybustransportation.dto.CarrierDTO;
import kz.iitu.intercitybustransportation.model.Carrier;
import org.springframework.stereotype.Component;

@Component
public class CarrierMapper {

    public CarrierDTO toDto(Carrier carrier) {
        CarrierDTO carrierDto = new CarrierDTO();
        carrierDto.setId(carrier.getId());
        carrierDto.setBusCompanyName(carrier.getBusCompanyName());
        carrierDto.setPhoneNumber(carrier.getPhoneNumber());
        carrierDto.setEmail(carrier.getEmail());
        carrierDto.setWebsite(carrier.getWebsite());
        return carrierDto;
    }

    public Carrier toEntity(CarrierDTO carrierDto) {
        Carrier carrier = new Carrier();
        carrier.setId(carrierDto.getId());
        carrier.setBusCompanyName(carrierDto.getBusCompanyName());
        carrier.setPhoneNumber(carrierDto.getPhoneNumber());
        carrier.setEmail(carrierDto.getEmail());
        carrier.setWebsite(carrierDto.getWebsite());
        return carrier;
    }
}