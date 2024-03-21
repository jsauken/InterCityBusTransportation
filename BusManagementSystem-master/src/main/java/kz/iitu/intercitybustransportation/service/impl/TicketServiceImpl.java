package kz.iitu.intercitybustransportation.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.iitu.intercitybustransportation.dto.ApiErrorDTO;
import kz.iitu.intercitybustransportation.dto.TicketDTO;
import kz.iitu.intercitybustransportation.dto.TicketResponseDTO;
import kz.iitu.intercitybustransportation.exceptions.NoAccessException;
import kz.iitu.intercitybustransportation.exceptions.ResourceNotFoundException;
import kz.iitu.intercitybustransportation.exceptions.SeatIsNotEmptyException;
import kz.iitu.intercitybustransportation.mapper.TicketMapper;
import kz.iitu.intercitybustransportation.model.Flight;
import kz.iitu.intercitybustransportation.model.Ticket;
import kz.iitu.intercitybustransportation.model.User;
import kz.iitu.intercitybustransportation.model.enums.TicketStatus;
import kz.iitu.intercitybustransportation.repository.FlightRepository;
import kz.iitu.intercitybustransportation.repository.TicketRepository;
import kz.iitu.intercitybustransportation.repository.UserRepository;
import kz.iitu.intercitybustransportation.security.JwtHelper;
import kz.iitu.intercitybustransportation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    private final TicketMapper ticketMapper;


    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, ObjectMapper objectMapper, UserRepository userRepository, FlightRepository flightRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public TicketResponseDTO getTicket(Long id) {
        return ticketRepository.findById(id)
                .map(ticketMapper::entToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id " + id));
    }

    @Override
    public List<TicketResponseDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::entToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO createTicket(TicketDTO ticketDto) {
        Ticket ticket = ticketMapper.toEntity(ticketDto);
        ticket.setUser(userRepository.getReferenceById(ticketDto.getUserId()));
        ticket.setTicketStatus(TicketStatus.PENDING);
        ticket.setPrice(flightRepository.getReferenceById(ticketDto.getFlightId()).getPrice());
        ticket.setFlight(flightRepository.getReferenceById(ticketDto.getFlightId()));
        ticket.setBookingTime(LocalDateTime.now());
        String qrCode = UUID.randomUUID().toString();
        ticket.setQrCode(qrCode); //??????
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDto(savedTicket);
    }

    @Override
    public TicketDTO updateTicket(Long id, TicketDTO ticketDto) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    // Update the fields of the ticket as per your requirements
                    Ticket updatedTicket = ticketRepository.save(ticket);
                    return ticketMapper.toDto(updatedTicket);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id " + id));
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.findById(id)
                .ifPresentOrElse(ticketRepository::delete, () -> {
                    throw new ResourceNotFoundException("Ticket not found with id " + id);
                });
    }

    @Override
    public TicketDTO bookTicket(Long userId, TicketDTO ticketDto) {


        if (Objects.equals(userId, ticketDto.getUserId())) {
            System.out.println("OK");
        } else {
            System.out.println("Not User");
        }
        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setUser(userRepository.getReferenceById(ticketDto.getUserId()));
        ticket.setTicketStatus(TicketStatus.CONFIRMED);
        ticket.setPrice(flightRepository.getReferenceById(ticketDto.getFlightId()).getPrice());

        ticket.setFlight(flightRepository.getReferenceById(ticketDto.getFlightId()));
        ticket.setBookingTime(LocalDateTime.now());
        ticket.setSeatNumber(ticketDto.getSeatNumber());
        String qrCode = UUID.randomUUID().toString();
        ticket.setQrCode(qrCode);
        Flight flight = flightRepository.getReferenceById(ticketDto.getFlightId());
        List<Ticket> tickets = flight.getTickets();
        flight.getTickets().add(ticket);
        flightRepository.save(flight);

        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDto(savedTicket);
    }


    @Override
    public TicketDTO bookTicketForFlight(Long flightId, Integer seat) throws IOException {

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();
        try {
            String authHeader = request.getHeader("Authorization");
            String token;
            String email = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                email = JwtHelper.extractUsername(token);
            }
            if (!seatAvailable(flightRepository.getById(flightId), seat)) {
                throw new SeatIsNotEmptyException("Seat is not Empty!");
            }
            Ticket ticket = new Ticket();
            ticket.setUser(userRepository.getByEmail(email));
            ticket.setTicketStatus(TicketStatus.CONFIRMED);
            ticket.setPrice(flightRepository.getReferenceById(flightId).getPrice());
            ticket.setFlight(flightRepository.getReferenceById(flightId));
            ticket.setBookingTime(LocalDateTime.now());
            ticket.setSeatNumber(seat);
            String qrCode = UUID.randomUUID().toString();
            ticket.setQrCode(qrCode); //??????
            Ticket savedTicket = ticketRepository.save(ticket);
            return ticketMapper.toDto(savedTicket);

        } catch (NoAccessException e) {
            ApiErrorDTO errorResponse = new ApiErrorDTO(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(toJson(errorResponse));
            return (TicketDTO) response;
        }

    }

    @Override
    public TicketDTO cancelTicket(Long ticketId) throws IOException {


        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();
        try {
            String authHeader = request.getHeader("Authorization");
            String token;
            String email = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                email = JwtHelper.extractUsername(token);
            }
            Ticket ticket = ticketRepository.getReferenceById(ticketId);
            ticket.setTicketStatus(TicketStatus.CONFIRMED);

            Ticket savedTicket = ticketRepository.save(ticket);
            return ticketMapper.toDto(savedTicket);

        } catch (NoAccessException e) {
            ApiErrorDTO errorResponse = new ApiErrorDTO(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(toJson(errorResponse));
            return (TicketDTO) response;
        }

    }

    @Override
    public List<TicketResponseDTO> showMyTickets() throws IOException {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();
        try {
            String authHeader = request.getHeader("Authorization");
            String token;
            String email = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                email = JwtHelper.extractUsername(token);
            }

           User currentUser = userRepository.getByEmail(email);
            return ticketRepository.findAllByUser(currentUser).stream()
                    .map(ticketMapper::entToDto)
                    .collect(Collectors.toList());

        } catch (NoAccessException e) {
            ApiErrorDTO errorResponse = new ApiErrorDTO(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(toJson(errorResponse));
            return null;
        }

    }

    private String toJson(ApiErrorDTO response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            return ""; // Return an empty string if serialization fails
        }
    }


    private boolean seatAvailable(Flight flight, int seat) {


        for (Ticket ticket : flight.getTickets()) {
            if (ticket.getSeatNumber() == seat) {
                return false;
            }
        }
        return true;
    }

}
