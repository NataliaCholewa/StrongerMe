package strongerme.service;

import org.springframework.stereotype.Service;

import strongerme.dto.ExerciseDto;
import strongerme.dto.ExerciseResponseDto;
import strongerme.exception.ApiException;
import strongerme.model.Exercise;
import strongerme.repository.ExerciseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllWithCategory() {
        return exerciseRepository.findAllWithCategory();
    }

    public Exercise createExercise(Exercise exercise) { 
        Optional<Exercise> existing = exerciseRepository.findByName(exercise.getName());
        if (existing.isPresent()) {
            throw new ApiException("Exercise with that name already exists", 400);
        }

        return exerciseRepository.save(exercise);
    }

    public Exercise getByName(String name) {
        return exerciseRepository.findByName(name).orElseThrow(() -> new ApiException("Exercise not found", 404));
    }


    public Exercise getById(UUID id) {
        return exerciseRepository.findById(id)
            .orElseThrow(() -> new ApiException("Exercise not found", 404));
    }

    public void deleteById(UUID id) {
        if (!exerciseRepository.existsById(id)) {
            throw new ApiException("Exercise not found", 404);
        }
        exerciseRepository.deleteById(id);
    }

    public List<ExerciseResponseDto> getAllAsDto() {
    List<Exercise> exercises = exerciseRepository.findAllWithCategory();

    return exercises.stream().map(ex -> {
        ExerciseResponseDto dto = new ExerciseResponseDto();
        dto.setId(ex.getId());
        dto.setName(ex.getName());
        dto.setDescription(ex.getDescription());
        dto.setImageUrl(ex.getImageUrl());
        dto.setUnilateral(ex.isUnilateral());

        if (ex.getCategory() != null) {
            dto.setCategoryName(ex.getCategory().getName());
        }

        return dto;
    }).toList();
}





}
