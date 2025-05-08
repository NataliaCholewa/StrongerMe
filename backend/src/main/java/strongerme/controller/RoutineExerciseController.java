package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.RoutineExercise;
import strongerme.service.RoutineExerciseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/routine-exercises")
public class RoutineExerciseController {

    private final RoutineExerciseService routineExerciseService;

    public RoutineExerciseController(RoutineExerciseService routineExerciseService) {
        this.routineExerciseService = routineExerciseService;
    }

    @GetMapping
    public List<RoutineExercise> getAll() {
        return routineExerciseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineExercise> getById(@PathVariable UUID id) {
        Optional<RoutineExercise> found = routineExerciseService.getById(id);
        return found.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RoutineExercise create(@RequestBody RoutineExercise routineExercise) {
        return routineExerciseService.save(routineExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        routineExerciseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
