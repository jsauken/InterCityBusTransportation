package kz.iitu.intercitybustransportation.dto;

import kz.iitu.intercitybustransportation.model.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketResponseDTO {
    private Long id;
    private String userEmail;

    private FlightDTO flightDTO;

    private TicketStatus ticketStatus;
    private int seatNumber;

}