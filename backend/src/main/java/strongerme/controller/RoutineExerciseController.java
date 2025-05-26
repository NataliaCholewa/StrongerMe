package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.RoutineExercise;
import strongerme.service.RoutineExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/routine-exercises")
public class RoutineExerciseController {

    private final RoutineExerciseService routineExerciseService;

    public RoutineExerciseController(RoutineExerciseService routineExerciseService) {
        this.routineExerciseService = routineExerciseService;
    }

    @Operation(summary = "Pobiera wszystkie przypisane ćwiczenia w planie")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista przypisanych ćwiczeń została zwrócona"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping
    public List<RoutineExercise> getAll() {
        return routineExerciseService.getAll();
    }

    @Operation(summary = "Pobiera przypisane ćwiczenie w planie po ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ćwiczenie zostało znalezione"),
    @ApiResponse(responseCode = "404", description = "Ćwiczenie nie istnieje"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping("/{id}")
    public ResponseEntity<RoutineExercise> getById(@PathVariable UUID id) {
        RoutineExercise found = routineExerciseService.getById(id);
        return ResponseEntity.ok(found);
    }

    @Operation(summary = "Dodaje nowe przypisane ćwiczenie do planu")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ćwiczenie zostało dodane"),
    @ApiResponse(responseCode = "400", description = "Nieprawidłowe dane wejściowe"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @PostMapping
    public RoutineExercise create(@RequestBody RoutineExercise routineExercise) {
        return routineExerciseService.save(routineExercise);
    }

    @Operation(summary = "Usuwa przypisane ćwiczenie z planu po ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Ćwiczenie zostało usunięte"),
    @ApiResponse(responseCode = "404", description = "Ćwiczenie nie istnieje"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        routineExerciseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
