package strongerme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import strongerme.dto.ExerciseDto;
import strongerme.model.Exercise;
import strongerme.model.ExerciseCategory;
import strongerme.repository.ExerciseRepository;
import strongerme.service.ExerciseCategoryService;
import strongerme.service.ExerciseService;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseCategoryService categoryService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, ExerciseRepository exerciseRepository, ExerciseCategoryService categoryService) {
        this.exerciseService = exerciseService;
        this.exerciseRepository = exerciseRepository;
        this.categoryService = categoryService;
    }

    @Operation(summary = "Pobiera wszystkie ćwiczenia", description = "Zwraca listę wszystkich ćwiczeń z bazy danych")
    @ApiResponse(responseCode = "200", description = "Lista ćwiczeń została zwrócona")

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllWithCategory();
    }

    @Operation(summary = "Tworzy nowe ćwiczenie", description = "Dodaje nowe ćwiczenie na podstawie przesłanych danych")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Ćwiczenie utworzone pomyślnie"),
    @ApiResponse(responseCode = "400", description = "Ćwiczenie o podanej nazwie już istnieje", content = @Content)
    })

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody ExerciseDto dto) {
        Exercise exercise = new Exercise();
        exercise.setName(dto.getName());
        exercise.setDescription(dto.getDescription());
        exercise.setImageUrl(dto.getImageUrl());
        exercise.setUnilateral(dto.isUnilateral());

        ExerciseCategory category = categoryService.getById(dto.getCategoryId());
        exercise.setCategory(category);

        Exercise saved = exerciseRepository.save(exercise);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Pobiera ćwiczenie po ID", description = "Zwraca ćwiczenie o podanym UUID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ćwiczenie znalezione"),
    @ApiResponse(responseCode = "404", description = "Ćwiczenie nie istnieje", content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable UUID id) {
        Exercise exercise = exerciseService.getById(id);
        return ResponseEntity.ok(exercise);
    }

    @Operation(summary = "Pobiera ćwiczenie po nazwie", description = "Zwraca ćwiczenie na podstawie jego nazwy")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Ćwiczenie znalezione"),
    @ApiResponse(responseCode = "404", description = "Ćwiczenie nie istnieje")
    })

    @GetMapping("/name")
    public Exercise getExerciseByName(@RequestParam String name) {
        return exerciseService.getByName(name);
    }

    @Operation(summary = "Usuwa ćwiczenie po ID", description = "Usuwa ćwiczenie na podstawie UUID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Ćwiczenie usunięte"),
    @ApiResponse(responseCode = "404", description = "Ćwiczenie nie istnieje")
    })

    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable UUID id) {
        exerciseService.deleteById(id);
    }

}
