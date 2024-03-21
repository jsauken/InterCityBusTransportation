package kz.iitu.intercitybustransportation.dto;

import kz.iitu.intercitybustransportation.model.Carrier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusDTO {
    private Long id;
    private String brand;
    private Integer capacity;
    private String registrationNumber;
    private String amenities;
    private Carrier carrier;
}
