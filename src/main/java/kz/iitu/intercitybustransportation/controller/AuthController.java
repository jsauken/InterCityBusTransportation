package kz.iitu.intercitybustransportation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kz.iitu.intercitybustransportation.dto.*;

import kz.iitu.intercitybustransportation.model.LoginAttempt;
import kz.iitu.intercitybustransportation.security.JwtHelper;
import kz.iitu.intercitybustransportation.service.UserService;
import kz.iitu.intercitybustransportation.service.impl.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final LoginService loginService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, LoginService loginService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.loginService = loginService;
    }

    @Operation(summary = "Signup user")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupDTO requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Authenticate user and return token")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = LoginResponseDTO.class)))
    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        } catch (BadCredentialsException e) {
            loginService.addLoginAttempt(request.email(), false);
            throw e;
        }

        String token = JwtHelper.generateToken(request.email());
        loginService.addLoginAttempt(request.email(), true);
        return ResponseEntity.ok(new LoginResponseDTO(request.email(), token));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, @RequestHeader("Authorization") String token) {
        // Extract the token from the Authorization header
        String extractedToken = token.replace("Bearer ", "");

        // Here you can invalidate the token, remove it from the client-side
        // For example, you can store the tokens in a blacklist or simply clear it from the client
        // In this example, we will just clear it from the client-side

        // Clear the token from the client-side by setting it to null or empty
        // This will effectively "logout" the user
        // In a real-world scenario, you would also want to handle token blacklisting or revocation
        // This is just a basic example for clearing the token
        request.getSession().invalidate();

        // Respond with success message
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get recent login attempts")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = LoginResponseDTO.class)))
    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))//forbidden
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @GetMapping(value = "/loginAttempts")
    public ResponseEntity<List<LoginAttemptResponse>> loginAttempts(@RequestHeader("Authorization") String token) {
        String email = JwtHelper.extractUsername(token.replace("Bearer ", ""));
        List<LoginAttempt> loginAttempts = loginService.findRecentLoginAttempts(email);
        return ResponseEntity.ok(convertToDTOs(loginAttempts));
    }



    private List<LoginAttemptResponse> convertToDTOs(List<LoginAttempt> loginAttempts) {
        return loginAttempts.stream()
                .map(LoginAttemptResponse::convertToDTO)
                .collect(Collectors.toList());
    }
}