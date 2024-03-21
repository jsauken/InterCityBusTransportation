package kz.iitu.intercitybustransportation.model;

import jakarta.persistence.*;
import kz.iitu.intercitybustransportation.model.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Flight flight;

    private int seatNumber;

    private LocalDateTime bookingTime;

    private TicketStatus ticketStatus;
    private Double price; //final price


    private String qrCode;


}