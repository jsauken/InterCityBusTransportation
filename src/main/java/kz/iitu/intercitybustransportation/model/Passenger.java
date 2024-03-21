package kz.iitu.intercitybustransportation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private Date birthOfDate;
    @ManyToOne
    private User user;
    @OneToOne
    private Ticket ticket;
}