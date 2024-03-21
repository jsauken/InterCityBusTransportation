package kz.iitu.intercitybustransportation.controller;
import kz.iitu.intercitybustransportation.dto.TicketDTO;
import kz.iitu.intercitybustransportation.dto.TicketResponseDTO;
import kz.iitu.intercitybustransportation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public TicketResponseDTO getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }


    @PostMapping("/book/{userId}")
    public ResponseEntity<TicketDTO> bookTicket(@PathVariable Long userId, @RequestBody TicketDTO ticketDto) {
        TicketDTO bookedTicket = ticketService.bookTicket(userId, ticketDto);
        return ResponseEntity.ok(bookedTicket);
    }

    @GetMapping("/book/{flightId}/{seatNumber}")
    public ResponseEntity<TicketDTO> bookTicket(@PathVariable Long flightId, @PathVariable int seatNumber) throws IOException {
        TicketDTO bookedTicket = ticketService.bookTicketForFlight(flightId, seatNumber);
        return ResponseEntity.ok(bookedTicket);
    }





    @GetMapping("/cancel/{ticketId}")
    public ResponseEntity<String> cancelTicket(@PathVariable Long ticketId) throws IOException {
        TicketDTO canceledTicket = ticketService.cancelTicket(ticketId);

        // Build the URI of the cancelled ticket
        URI ticketUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/tickets/{id}")
                .buildAndExpand(canceledTicket.getId())
                .toUri();

        return ResponseEntity.ok("Ticket with id " + ticketId + " has been cancelled. You can view the ticket at " + ticketUri.toString());
    }



    @GetMapping
    public List<TicketResponseDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("my_tickets")
    public List<TicketResponseDTO> myTickets() throws IOException {
        return ticketService.showMyTickets();
    }

    @PostMapping
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDto) {
        return ticketService.createTicket(ticketDto);
    }

    @PutMapping("/{id}")
    public TicketDTO updateTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDto) {
        return ticketService.updateTicket(id, ticketDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }
}
