package kz.iitu.intercitybustransportation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "buses")
public class Bus {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String brand;
    private Integer capacity;

    private String registrationNumber;

    private String amenities;

    @ManyToOne
    private Carrier carrier;
}