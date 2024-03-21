package kz.iitu.intercitybustransportation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteDTO {
    private Long id;
    private String origin;
    private String destination;
    private Double duration;
    private List<StopDTO> stops;
}
