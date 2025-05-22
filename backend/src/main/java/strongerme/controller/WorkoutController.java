package strongerme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.Workout;
import strongerme.service.WorkoutService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @Operation(summary = "Pobiera wszystkie treningi", description = "Zwraca listę wszystkich treningów")
    @ApiResponse(responseCode = "200", description = "Lista treningów została zwrócona")

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @Operation(summary = "Pobiera trening po ID", description = "Zwraca trening na podstawie UUID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Trening znaleziony"),
    @ApiResponse(responseCode = "404", description = "Trening nie istnieje")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable UUID id) {
        Workout workout = workoutService.getWorkoutById(id);
        return ResponseEntity.ok(workout);
    }

    @Operation(summary = "Tworzy nowy trening", description = "Dodaje nowy trening na podstawie przesłanych danych")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Trening został utworzony"),
    @ApiResponse(responseCode = "400", description = "Nieprawidłowe dane wejściowe")
    })

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
        Workout saved = workoutService.saveWorkout(workout);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Usuwa trening po ID", description = "Usuwa trening na podstawie UUID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Trening usunięty"),
    @ApiResponse(responseCode = "404", description = "Trening nie istnieje")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable UUID id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }
}
