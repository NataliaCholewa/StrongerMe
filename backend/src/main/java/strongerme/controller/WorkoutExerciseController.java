package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.WorkoutExercise;
import strongerme.service.WorkoutExerciseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/workout-exercises")
public class WorkoutExerciseController {

    private final WorkoutExerciseService workoutExerciseService;

    public WorkoutExerciseController(WorkoutExerciseService workoutExerciseService) {
        this.workoutExerciseService = workoutExerciseService;
    }

    @Operation(summary = "Pobiera wszystkie ćwiczenia przypisane do treningów")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista ćwiczeń w treningach została zwrócona"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping
    public List<WorkoutExercise> getAllWorkoutExercises() {
        return workoutExerciseService.getAllWorkoutExercises();
    }

    @Operation(summary = "Pobiera ćwiczenie w treningu na podstawie ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ćwiczenie zostało znalezione"),
    @ApiResponse(responseCode = "404", description = "Ćwiczenie nie istnieje"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutExercise> getWorkoutExerciseById(@PathVariable UUID id) {
        WorkoutExercise workoutExercise = workoutExerciseService.getWorkoutExerciseById(id);
        return ResponseEntity.ok(workoutExercise);
    }

    @Operation(summary = "Dodaje nowe ćwiczenie do treningu")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ćwiczenie zostało dodane do treningu"),
    @ApiResponse(responseCode = "400", description = "Nieprawidłowe dane wejściowe"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @PostMapping
    public ResponseEntity<WorkoutExercise> createWorkoutExercise(@RequestBody WorkoutExercise workoutExercise) {
        WorkoutExercise created = workoutExerciseService.createWorkoutExercise(workoutExercise);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "USUWA ćwiczenie z treningu na podstawie ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Ćwiczenie zostało usunięte"),
    @ApiResponse(responseCode = "404", description = "Ćwiczenie nie zostało znalezione"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutExercise(@PathVariable UUID id) {
        workoutExerciseService.deleteWorkoutExercise(id);
        return ResponseEntity.noContent().build();
    }
}
