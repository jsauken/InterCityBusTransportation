package kz.iitu.intercitybustransportation.service;

import kz.iitu.intercitybustransportation.dto.TicketDTO;
import kz.iitu.intercitybustransportation.dto.TicketResponseDTO;

import java.io.IOException;
import java.util.List;

public interface TicketService {
    TicketResponseDTO getTicket(Long id);
    List<TicketResponseDTO> getAllTickets();
    TicketDTO createTicket(TicketDTO ticketDto);
    TicketDTO updateTicket(Long id, TicketDTO ticketDto);
    void deleteTicket(Long id);

    TicketDTO bookTicket(Long userId, TicketDTO ticketDto);
    TicketDTO bookTicketForFlight(Long flightId, Integer seat) throws IOException;
    TicketDTO cancelTicket(Long ticketId) throws IOException;
    List<TicketResponseDTO> showMyTickets() throws IOException;
}
