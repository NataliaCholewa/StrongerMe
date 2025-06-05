package strongerme.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import strongerme.dto.WorkoutDetailsDto;
import strongerme.dto.WorkoutExerciseRequest;
import strongerme.dto.WorkoutRequest;
import strongerme.exception.ApiException;
import strongerme.model.*;
import strongerme.repository.ExerciseRepository;
import strongerme.repository.WorkoutRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout getWorkoutById(UUID id) {
        return workoutRepository.findById(id)
                .orElseThrow(() -> new ApiException("Workout not found", 404));
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

    @Transactional
    public Workout createWorkout(WorkoutRequest request, User user) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ApiException("Workout name is required", 400);
        }

        Workout workout = new Workout();
        workout.setName(request.getName());
        workout.setDescription(request.getDescription());
        workout.setUser(user);

        List<WorkoutExercise> workoutExercises = new ArrayList<>();

        for (WorkoutExerciseRequest req : request.getExercises()) {
            Exercise exercise = exerciseRepository.findById(req.getExerciseId())
                    .orElseThrow(() -> new ApiException("Exercise not found", 404));

            WorkoutExercise we = new WorkoutExercise();
            we.setExercise(exercise);
            we.setWorkout(workout);
            we.setSets(req.getSets());
            we.setReps(req.getReps());
            we.setWeight(req.getWeight());

            workoutExercises.add(we);
        }

        workout.setWorkoutExercises(workoutExercises);
        return workoutRepository.save(workout);
    }
}
