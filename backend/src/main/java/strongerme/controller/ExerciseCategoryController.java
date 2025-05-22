package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strongerme.model.ExerciseCategory;
import strongerme.service.ExerciseCategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class ExerciseCategoryController {

    private final ExerciseCategoryService service;

    public ExerciseCategoryController(ExerciseCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<ExerciseCategory> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseCategory> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));

    }
}
