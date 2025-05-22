package strongerme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.Exercise;
import strongerme.service.ExerciseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @PostMapping
    public Exercise createExercise(@RequestBody Exercise exercise) {
        return exerciseService.createExercise(exercise);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable UUID id) {
        Exercise exercise = exerciseService.getById(id);
        return ResponseEntity.ok(exercise);
    }


    @GetMapping("/name")
    public Exercise getExerciseByName(@RequestParam String name) {
        return exerciseService.getByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable UUID id) {
        exerciseService.deleteById(id);
    }

}
