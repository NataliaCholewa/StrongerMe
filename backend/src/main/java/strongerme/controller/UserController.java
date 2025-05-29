package strongerme.controller;

import strongerme.dto.UserDTO;
import strongerme.model.Exercise;
import strongerme.model.User;
import strongerme.dto.UserDTO;
import strongerme.repository.UserRepository;
import strongerme.service.ExerciseService;
import strongerme.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Zwraca aktualnie zalogowanego użytkownika")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Użytkownik został zwrócony"),
        @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal(); 
        return ResponseEntity.ok(new UserDTO(user));
    }


    @Operation(summary = "Pobiera wszystkich użytkowników", description = "Zwraca listę wszystkich użytkowników z systemu")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista użytkowników została zwrócona")
    })

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Pobiera użytkownika po emailu", description = "Zwraca dane użytkownika na podstawie podanego emailu")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Użytkownik został znaleziony"),
    @ApiResponse(responseCode = "404", description = "Użytkownik nie istnieje"),
    @ApiResponse(responseCode = "500", description = "Błąd serwera", content = @Content)
    })

    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String value) {
    User user = userService.getByEmail(value); 
    return ResponseEntity.ok(user);
    }

    @Operation(summary = "Pobiera użytkownika po ID", description = "Zwraca dane użytkownika na podstawie podanego identyfikatora UUID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Użytkownik został znaleziony"),
    @ApiResponse(responseCode = "404", description = "Użytkownik nie istnieje"),
    @ApiResponse(responseCode = "500", description = "Błąd serwera", content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Usuwa użytkownika", description = "Usuwa użytkownika na podstawie ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Użytkownik został usunięty"),
    @ApiResponse(responseCode = "404", description = "Użytkownik nie został znaleziony")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Dodaje nowego użytkownika", description = "Tworzy nowego użytkownika na podstawie przesłanych danych")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Użytkownik został utworzony"),
    @ApiResponse(responseCode = "400", description = "Nieprawidłowe dane wejściowe")
    })

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
    User saved = userService.createUser(user);
    return ResponseEntity.ok(saved);
    }
}


