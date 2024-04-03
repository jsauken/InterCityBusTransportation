package kz.iitu.intercitybustransportation.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "stop")
public class Stop {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String location;  //city or landmark

    private LocalDateTime arrivalTime;
    private LocalDateTime  departureTime;

}
