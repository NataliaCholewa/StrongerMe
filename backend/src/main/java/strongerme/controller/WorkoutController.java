package strongerme.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.Workout;
import strongerme.dto.WorkoutDetailsDto;
import strongerme.dto.WorkoutRequest;
import strongerme.model.User;
import strongerme.service.WorkoutService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
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
    public ResponseEntity<WorkoutDetailsDto> getWorkoutById(@PathVariable UUID id) {
    Workout workout = workoutService.getWorkoutById(id);
    WorkoutDetailsDto dto = workoutService.mapToDto(workout);
    return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Zwraca treningi aktualnie zalogowanego użytkownika")
    @GetMapping("/me")
    public ResponseEntity<List<Workout>> getMyWorkouts() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return ResponseEntity.ok(workoutService.getWorkoutsByUserId(user.getId()));
    }

    @Operation(summary = "Tworzy nowy trening", description = "Dodaje nowy trening na podstawie przesłanych danych")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Trening został utworzony"),
    @ApiResponse(responseCode = "400", description = "Nieprawidłowe dane wejściowe")
    })

    @PostMapping
    public ResponseEntity<?> createWorkout(
        @RequestBody WorkoutRequest request,
        @AuthenticationPrincipal User user) {
    Workout workout = workoutService.createWorkout(request, user);
    return ResponseEntity.ok(workout);
}


    @Operation(summary = "Edytuje trening", description = "edytuje trening na podstawie przesłanych danych")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Trening został zedytowany"),
    @ApiResponse(responseCode = "400", description = "Nieprawidłowe dane")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Workout> editWorkout(@PathVariable UUID id,
        @RequestBody Workout workout) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        workout.setUser(user);
        workout.setId(id);
        Workout edited = workoutService.editWorkout(workout);
        return ResponseEntity.ok(edited);
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
