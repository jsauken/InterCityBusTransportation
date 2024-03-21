package kz.iitu.intercitybustransportation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightDTO {
    private Long id;
    private Date departureTime;
    private Date arrivalTime;
    private RouteDTO route; // Assuming you have a RouteDto
    private BusDTO bus; // Assuming you have a BusDto
    private Double price;
    // getters and setters
}