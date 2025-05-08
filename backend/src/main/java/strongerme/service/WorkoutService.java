package strongerme.service;

import org.springframework.stereotype.Service;
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

    public Optional<Workout> getWorkoutById(UUID id) {
        return workoutRepository.findById(id);
    }

    public Workout saveWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public List<Workout> getWorkoutsByUserId(UUID userId) {
        return workoutRepository.findByUserId(userId);
    }

    public void deleteWorkout(UUID id) {
        workoutRepository.deleteById(id);
    }
}

