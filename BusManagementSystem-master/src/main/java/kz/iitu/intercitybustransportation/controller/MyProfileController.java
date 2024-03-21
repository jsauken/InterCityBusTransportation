package kz.iitu.intercitybustransportation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profile/my")
@RequiredArgsConstructor
@Validated
@Tag(name = "My profile controller")
public class MyProfileController {

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get profile information")
    @GetMapping
    public void get() {
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update profile information")
    @PutMapping("/update")
    public void update() {
    }


    @PutMapping("/update-photo")
    public void updatePhoto() {

    }
}
