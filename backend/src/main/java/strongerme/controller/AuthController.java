package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.dto.LoginRequest;
import strongerme.dto.RegisterRequest;
import strongerme.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Rejestruje nowego użytkownika")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Użytkownik został pomyślnie zarejestrowany"),
    @ApiResponse(responseCode = "400", description = "Błędne dane wejściowe")
    })

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @Operation(summary = "Loguje użytkownika i generuje token JWT")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Zwraca poprawnie wygenerowany token JWT"),
    @ApiResponse(responseCode = "401", description = "Błędne dane logowania")
    })

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
