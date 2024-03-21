package kz.iitu.intercitybustransportation.mapper;

import kz.iitu.intercitybustransportation.dto.BusDTO;
import kz.iitu.intercitybustransportation.model.Bus;
import kz.iitu.intercitybustransportation.service.BusService;
import kz.iitu.intercitybustransportation.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusMapper {
    private final CarrierService carrierService;

    @Autowired
    public BusMapper(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    public BusDTO toDto(Bus bus) {
        BusDTO busDto = new BusDTO();
        busDto.setId(bus.getId());
        busDto.setBrand(bus.getBrand());
        busDto.setCapacity(bus.getCapacity());
        busDto.setRegistrationNumber(bus.getRegistrationNumber());
        busDto.setAmenities(bus.getAmenities());
        busDto.setCarrier(bus.getCarrier()); // assuming Carrier has a proper toString() method or you have a CarrierDto
        return busDto;
    }

    public Bus toEntity(BusDTO busDto) {
        Bus bus = new Bus();
        bus.setId(busDto.getId());
        bus.setBrand(busDto.getBrand());
        bus.setCapacity(busDto.getCapacity());
        bus.setRegistrationNumber(busDto.getRegistrationNumber());
        bus.setAmenities(busDto.getAmenities());
        // For carrier, you would typically look up the entity based on the ID.
        // Assuming you have a service to do that:
//        bus.setCarrier(carrierService.getCarrier(busDto.getCarrier().getId()));
        return bus;
    }

}