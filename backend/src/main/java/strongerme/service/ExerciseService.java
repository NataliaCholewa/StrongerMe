package strongerme.service;

import org.springframework.stereotype.Service;
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
        return exerciseRepository.save(exercise);
    }

    public Optional<Exercise> getByName(String name) {
        return exerciseRepository.findByName(name);
    }
}
