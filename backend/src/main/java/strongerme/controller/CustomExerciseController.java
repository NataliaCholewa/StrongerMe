package strongerme.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.CustomExercise;
import strongerme.service.CustomExerciseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/custom-exercises")
public class CustomExerciseController {

    private final CustomExerciseService customExerciseService;

    public CustomExerciseController(CustomExerciseService customExerciseService) {
        this.customExerciseService = customExerciseService;
    }

    @Operation(summary = "POBIERA wszystkie niestandardowe ćwiczenia")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ćwiczenia zostały zwrócone pomyślnie")
    })

    @GetMapping
    public List<CustomExercise> getAll() {
        return customExerciseService.getAll();
    }

    @Operation(summary = "Pobiera ćwiczenie na podstawie ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ćwiczenie zostało znalezione"),
    @ApiResponse(responseCode = "404", description = "Ćwiczenie nie istnieje")
    })

    @GetMapping("/{id}")
    public CustomExercise getById(@PathVariable UUID id) {
        return customExerciseService.getById(id);
    }

    @Operation(summary = "Pobiera wszystkie ćwiczenia użytkownika")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ćwiczenia zostały zwrócone pomyślnie")
    })

    @GetMapping("/user/{userId}")
    public List<CustomExercise> getByUserId(@PathVariable UUID userId) {
        return customExerciseService.getAllByUserId(userId);
    }

    @Operation(summary = "DODAJE nowe ćwiczenie użytkownika")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Ćwiczenie zostało utworzone"),
    @ApiResponse(responseCode = "400", description = "Nieprawidłowe dane wejściowe")
    })

    @PostMapping
    public ResponseEntity<CustomExercise> create(@RequestBody CustomExercise customExercise) {
        CustomExercise saved = customExerciseService.save(customExercise);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "USUWA ćwiczenie użytkownika na podstawie ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Ćwiczenie zostało usunięte"),
    @ApiResponse(responseCode = "404", description = "Ćwiczenie nie istnieje")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        customExerciseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
