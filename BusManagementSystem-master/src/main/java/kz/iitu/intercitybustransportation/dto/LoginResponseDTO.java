package kz.iitu.intercitybustransportation.dto;


import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponseDTO(
        @Schema(description = "email")
        String email,
        @Schema(description = "JWT token")
        String token) {

}