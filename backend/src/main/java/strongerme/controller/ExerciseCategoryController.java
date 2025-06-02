package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import strongerme.model.ExerciseCategory;
import strongerme.service.ExerciseCategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/exercise-categories")
public class ExerciseCategoryController {

    private final ExerciseCategoryService service;

    public ExerciseCategoryController(ExerciseCategoryService service) {
        this.service = service;
    }

    @Operation(summary = "Pobiera wszystkie kategorie ćwiczeń")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista kategorii została zwrócona"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping
    public List<ExerciseCategory> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Pobiera kategorię ćwiczeń po ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Kategoria została znaleziona"),
    @ApiResponse(responseCode = "404", description = "Nie znaleziono kategorii"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseCategory> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));

    }
}
