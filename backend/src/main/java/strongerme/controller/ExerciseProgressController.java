package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.ExerciseProgress;
import strongerme.service.ExerciseProgressService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/progress")
public class ExerciseProgressController {

    private final ExerciseProgressService progressService;

    public ExerciseProgressController(ExerciseProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping
    public List<ExerciseProgress> getAllProgress() {
        return progressService.getAllProgress();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseProgress> getProgressById(@PathVariable UUID id) {
        ExerciseProgress progress = progressService.getById(id); 
        return ResponseEntity.ok(progress);
    }
    


    @GetMapping("/user/{userId}")
    public List<ExerciseProgress> getProgressByUser(@PathVariable UUID userId) {
        return progressService.getByUserId(userId);
    }

    @PostMapping
    public ExerciseProgress addProgress(@RequestBody ExerciseProgress progress) {
        return progressService.saveProgress(progress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable UUID id) {
        progressService.deleteProgress(id);
        return ResponseEntity.noContent().build();
    }
}
