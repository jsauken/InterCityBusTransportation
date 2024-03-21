package kz.iitu.intercitybustransportation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StopDTO {
    private Long id;
    private String location;  //city or landmark
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private RouteDTO route;
}
