package kz.iitu.intercitybustransportation.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "carrier")
public class Carrier {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String busCompanyName;

    private String phoneNumber;
    private String email;
    private String website;

}