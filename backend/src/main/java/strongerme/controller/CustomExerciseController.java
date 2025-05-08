package strongerme.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.CustomExercise;
import strongerme.service.CustomExerciseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/custom-exercises")
public class CustomExerciseController {

    private final CustomExerciseService customExerciseService;

    public CustomExerciseController(CustomExerciseService customExerciseService) {
        this.customExerciseService = customExerciseService;
    }

    @GetMapping
    public List<CustomExercise> getAll() {
        return customExerciseService.getAll();
    }

    @GetMapping("/{id}")
    public CustomExercise getById(@PathVariable UUID id) {
        return customExerciseService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<CustomExercise> getByUserId(@PathVariable UUID userId) {
        return customExerciseService.getAllByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<CustomExercise> create(@RequestBody CustomExercise customExercise) {
        CustomExercise saved = customExerciseService.save(customExercise);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        customExerciseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
