package strongerme.service;

import org.springframework.stereotype.Service;
import strongerme.exception.ApiException;
import strongerme.model.Workout;
import strongerme.repository.WorkoutRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout getWorkoutById(UUID id) {
        return workoutRepository.findById(id).orElseThrow(() -> new ApiException("Workout not found", 404));
    }


    public Workout saveWorkout(Workout workout) {
        if (workout.getName() == null || workout.getName().isEmpty()) {
            throw new ApiException("Workout name cannot be empty", 400);
        }
        return workoutRepository.save(workout);
    }

    public List<Workout> getWorkoutsByUserId(UUID userId) {
        List<Workout> workouts = workoutRepository.findByUserId(userId);
        if (workouts.isEmpty()) {
            throw new ApiException("No workouts found for this user", 404);
        }
        return workouts;
    }

    public void deleteWorkout(UUID id) {
        if (!workoutRepository.existsById(id)) {
            throw new ApiException("Workout not found", 404);
        }
        workoutRepository.deleteById(id);
    }
}

