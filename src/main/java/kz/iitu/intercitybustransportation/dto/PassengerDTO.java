package kz.iitu.intercitybustransportation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassengerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthOfDate;
    private UserDTO user; // Assuming you have a UserDto
    private TicketDTO ticket; // Assuming you have a TicketDto
    // getters and setters
}