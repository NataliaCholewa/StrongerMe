package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.ExerciseProgress;
import strongerme.service.ExerciseProgressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/progress")
public class ExerciseProgressController {

    private final ExerciseProgressService progressService;

    public ExerciseProgressController(ExerciseProgressService progressService) {
        this.progressService = progressService;
    }

    @Operation(summary = "Pobiera całą historię progresu ćwiczeń")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Progresy zwrócone pomyślnie"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping
    public List<ExerciseProgress> getAllProgress() {
        return progressService.getAllProgress();
    }

    @Operation(summary = "Pobiera progres ćwiczenia po ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Progres zwrócony pomyślnie"),
    @ApiResponse(responseCode = "404", description = "Progres nie istnieje"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseProgress> getProgressById(@PathVariable UUID id) {
        ExerciseProgress progress = progressService.getById(id); 
        return ResponseEntity.ok(progress);
    }
    
    @Operation(summary = "Pobiera progres ćwiczeń dla danego użytkownika")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Progres użytkownika zwrócony pomyślnie"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping("/user/{userId}")
    public List<ExerciseProgress> getProgressByUser(@PathVariable UUID userId) {
        return progressService.getByUserId(userId);
    }

    @Operation(summary = "Dodaje nowy progres ćwiczenia")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Progres dodany pomyślnie"),
    @ApiResponse(responseCode = "400", description = "Nieprawidłowe dane"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @PostMapping
    public ExerciseProgress addProgress(@RequestBody ExerciseProgress progress) {
        return progressService.saveProgress(progress);
    }

    @Operation(summary = "Usuwa progres ćwiczenia po ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Progres usunięty"),
    @ApiResponse(responseCode = "404", description = "Progres nie istnieje"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable UUID id) {
        progressService.deleteProgress(id);
        return ResponseEntity.noContent().build();
    }
}
