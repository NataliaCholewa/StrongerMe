package strongerme.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@SecurityRequirement(name = "bearerAuth")
@RestController
public class AdminController {


    @Operation(summary = "Endpoint dostępny tylko dla administratorów")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Użytkownik z rolą ADMIN uzyskał dostęp"),
    @ApiResponse(responseCode = "403", description = "Brak uprawnień – użytkownik nie jest administratorem"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji – użytkownik niezalogowany")
    })
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin-only")
    public String onlyAdmin() {
        return "Witaj, Admin!";
    }
}
