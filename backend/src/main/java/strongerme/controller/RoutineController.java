package strongerme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.Routine;
import strongerme.service.RoutineService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/routines")
public class RoutineController {

    private final RoutineService routineService;

    @Autowired
    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @Operation(summary = "Zwraca listę wszystkich planów treningowych")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista planów została zwrócona"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping
    public List<Routine> getAllRoutines() {
        return routineService.getAllRoutines();
    }

    @Operation(summary = "Zwraca plan treningowy po ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Plan został znaleziony"),
    @ApiResponse(responseCode = "404", description = "Plan nie istnieje"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Routine> getRoutineById(@PathVariable UUID id) {
        Routine routine = routineService.getRoutineById(id); 
        return ResponseEntity.ok(routine);
    }

    @Operation(summary = "Tworzy nowy plan treningowy")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Plan został utworzony"),
    @ApiResponse(responseCode = "400", description = "Nieprawidłowe dane wejściowe"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @PostMapping
    public ResponseEntity<Routine> createRoutine(@RequestBody Routine routine) {
        Routine saved = routineService.saveRoutine(routine);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Usuwa plan treningowy po ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Plan został usunięty"),
    @ApiResponse(responseCode = "404", description = "Plan nie został znaleziony"),
    @ApiResponse(responseCode = "401", description = "Brak autoryzacji")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable UUID id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }
}
