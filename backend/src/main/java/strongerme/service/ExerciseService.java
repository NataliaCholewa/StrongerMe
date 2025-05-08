package strongerme.service;

import org.springframework.stereotype.Service;
import strongerme.exception.ApiException;
import strongerme.model.Exercise;
import strongerme.repository.ExerciseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
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


    // jeszcze delete
}
