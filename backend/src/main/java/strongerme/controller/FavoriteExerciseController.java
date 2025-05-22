package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.FavoriteExercise;
import strongerme.service.FavoriteExerciseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/favorite-exercises")
public class FavoriteExerciseController {

    private final FavoriteExerciseService service;

    public FavoriteExerciseController(FavoriteExerciseService service) {
        this.service = service;
    }

    @GetMapping
    public List<FavoriteExercise> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteExercise> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));

    }

    @GetMapping("/user/{userId}")
    public List<FavoriteExercise> getByUserId(@PathVariable UUID userId) {
        return service.getByUserId(userId);
    }

    @PostMapping
    public FavoriteExercise create(@RequestBody FavoriteExercise favoriteExercise) {
        return service.save(favoriteExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
 