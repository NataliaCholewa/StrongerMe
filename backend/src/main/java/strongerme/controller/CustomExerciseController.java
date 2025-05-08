package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.CustomExercise;
import strongerme.service.CustomExerciseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/custom-exercises")
public class CustomExerciseController {

    private final CustomExerciseService service;

    public CustomExerciseController(CustomExerciseService service) {
        this.service = service;
    }

    @GetMapping
    public List<CustomExercise> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomExercise> getById(@PathVariable UUID id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<CustomExercise> getByUserId(@PathVariable UUID userId) {
        return service.getByUserId(userId);
    }

    @PostMapping
    public CustomExercise create(@RequestBody CustomExercise customExercise) {
        return service.save(customExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
