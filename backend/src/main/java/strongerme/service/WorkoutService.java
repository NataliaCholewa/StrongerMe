package strongerme.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import strongerme.dto.WorkoutDetailsDto;
import strongerme.exception.ApiException;
import strongerme.model.User;
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

    public Workout editWorkout(Workout workout) {
        if (workout.getName() == null || workout.getName().isEmpty()) {
            throw new ApiException("Workout name cannot be empty", 400);
        }
        Workout existing = workoutRepository.findById(workout.getId())
            .orElseThrow(() -> new ApiException("Workout not found", 404));
        return workoutRepository.save(workout);
        
    }

    public void deleteWorkout(UUID id) {
        if (!workoutRepository.existsById(id)) {
            throw new ApiException("Workout not found", 404);
        }
        workoutRepository.deleteById(id);
    }

    public WorkoutDetailsDto mapToDto(Workout workout) {
    WorkoutDetailsDto dto = new WorkoutDetailsDto();
    dto.setId(workout.getId());
    dto.setName(workout.getName());
    dto.setDescription(workout.getDescription());
    dto.setPerformedAt(workout.getPerformedAt());

    List<WorkoutDetailsDto.ExerciseEntry> entries = workout.getWorkoutExercises()
        .stream()
        .map(we -> {
            WorkoutDetailsDto.ExerciseEntry entry = new WorkoutDetailsDto.ExerciseEntry();
            entry.setId(we.getId());
            entry.setName(we.getExercise().getName());
            entry.setSets(we.getSets());
            entry.setReps(we.getReps());
            entry.setWeight(we.getWeight());
            return entry;
        })
        .toList();

    dto.setWorkoutExercises(entries);
    return dto;
}

}

