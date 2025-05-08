package strongerme.service;

import org.springframework.stereotype.Service;
import strongerme.exception.ApiException;
import strongerme.model.WorkoutExercise;
import strongerme.repository.WorkoutExerciseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkoutExerciseService {

    private final WorkoutExerciseRepository workoutExerciseRepository;

    public WorkoutExerciseService(WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    public List<WorkoutExercise> getAllWorkoutExercises() {
        return workoutExerciseRepository.findAll();
    }

    public WorkoutExercise getWorkoutExerciseById(UUID id) {
        return workoutExerciseRepository.findById(id).orElseThrow(() -> new ApiException("WorkoutExercise not found", 404));
    }

    public WorkoutExercise createWorkoutExercise(WorkoutExercise workoutExercise) {
        return workoutExerciseRepository.save(workoutExercise);
    }

    public void deleteWorkoutExercise(UUID id) {
        if (!workoutExerciseRepository.existsById(id)) {
            throw new ApiException("WorkoutExercise not found", 404);
        }
        workoutExerciseRepository.deleteById(id);
    }
}
