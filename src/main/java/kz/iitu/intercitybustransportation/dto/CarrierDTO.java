package kz.iitu.intercitybustransportation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarrierDTO {
    private Long id;
    private String busCompanyName;
    private String phoneNumber;
    private String email;
    private String website;
}