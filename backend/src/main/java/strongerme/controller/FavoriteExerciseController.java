package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.FavoriteExercise;
import strongerme.service.FavoriteExerciseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/favorite-exercises")
public class FavoriteExerciseController {

    private final FavoriteExerciseService service;

    public FavoriteExerciseController(FavoriteExerciseService service) {
        this.service = service;
    }

    @Operation(summary = "Pobiera wszystkie ulubione ćwiczenia")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Pobrano listę ulubionych ćwiczeń")
    })

    @GetMapping
    public List<FavoriteExercise> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Pobiera ulubione ćwiczenie po ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Zwrócono ćwiczenie"),
    @ApiResponse(responseCode = "404", description = "Ćwiczenie nie istnieje")
    })

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteExercise> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));

    }

    @Operation(summary = "Pobiera ulubione ćwiczenia użytkownika")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Zwrócono ulubione ćwiczenia użytkownika")
    })

    @GetMapping("/user/{userId}")
    public List<FavoriteExercise> getByUserId(@PathVariable UUID userId) {
        return service.getByUserId(userId);
    }

    @Operation(summary = "Dodaje nowe ulubione ćwiczenie")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ćwiczenie zostało dodane do ulubionych"),
    @ApiResponse(responseCode = "400", description = "Nieprawidłowe dane wejściowe")
    })

    @PostMapping
    public FavoriteExercise create(@RequestBody FavoriteExercise favoriteExercise) {
        return service.save(favoriteExercise);
    }

    @Operation(summary = "Usuwa ulubione ćwiczenie po ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Ćwiczenie zostało usunięte"),
    @ApiResponse(responseCode = "404", description = "Ćwiczenie nie istnieje")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
 