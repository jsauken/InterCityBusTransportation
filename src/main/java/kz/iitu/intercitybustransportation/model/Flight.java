package kz.iitu.intercitybustransportation.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Date departureTime;
    private Date arrivalTime;

    @ManyToOne
    private Route route;

    @ManyToOne
    private Bus bus;

    private Double price;


    @ManyToMany
    private List<Ticket> tickets;
}
