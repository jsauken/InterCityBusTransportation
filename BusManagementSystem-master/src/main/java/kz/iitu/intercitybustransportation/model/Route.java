package kz.iitu.intercitybustransportation.model;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String origin;
    private String destination;
    private Double duration; //estimated travel time

    @OneToMany
    private List<Stop> stops;
}