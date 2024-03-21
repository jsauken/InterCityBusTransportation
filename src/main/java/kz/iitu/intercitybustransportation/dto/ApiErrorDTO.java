package kz.iitu.intercitybustransportation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ApiErrorDTO(
        @Schema(description = "Error code")
        int errorCode,
        @Schema(description = "Error description")
        String description) {

}