package strongerme.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import strongerme.dto.WorkoutRequest;
import strongerme.dto.WorkoutExerciseRequest;
import strongerme.exception.ApiException;
import strongerme.model.Exercise;
import strongerme.model.User;
import strongerme.model.Workout;
import strongerme.model.WorkoutExercise;
import strongerme.repository.ExerciseRepository;
import strongerme.repository.WorkoutRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkoutServiceTest {

    @InjectMocks
    private WorkoutService workoutService;

    @Mock
    private WorkoutRepository workoutRepository;

    @Mock
    private ExerciseRepository exerciseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getWorkoutById_ShouldReturnWorkout() {
        UUID workoutId = UUID.randomUUID();
        Workout mockWorkout = new Workout();
        mockWorkout.setId(workoutId);
        when(workoutRepository.findById(workoutId)).thenReturn(Optional.of(mockWorkout));

        Workout result = workoutService.getWorkoutById(workoutId);

        assertEquals(workoutId, result.getId());
    }

    @Test
    void getWorkoutById_ShouldThrow_WhenNotFound() {
        UUID workoutId = UUID.randomUUID();
        when(workoutRepository.findById(workoutId)).thenReturn(Optional.empty());

        assertThrows(ApiException.class, () -> workoutService.getWorkoutById(workoutId));
    }

    @Test
    void saveWorkout_ShouldSaveSuccessfully() {
        Workout workout = new Workout();
        workout.setName("Test Workout");
        when(workoutRepository.save(workout)).thenReturn(workout);

        Workout result = workoutService.saveWorkout(workout);
        assertEquals("Test Workout", result.getName());
    }

    @Test
    void saveWorkout_ShouldThrow_WhenNameEmpty() {
        Workout workout = new Workout();
        workout.setName("");
        assertThrows(ApiException.class, () -> workoutService.saveWorkout(workout));
    }

    @Test
    void deleteWorkout_ShouldDeleteSuccessfully() {
        UUID id = UUID.randomUUID();
        when(workoutRepository.existsById(id)).thenReturn(true);
        workoutService.deleteWorkout(id);
        verify(workoutRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteWorkout_ShouldThrow_WhenNotExists() {
        UUID id = UUID.randomUUID();
        when(workoutRepository.existsById(id)).thenReturn(false);
        assertThrows(ApiException.class, () -> workoutService.deleteWorkout(id));
    }

    @Test
    void getWorkoutsByUserId_ShouldReturnList() {
        UUID userId = UUID.randomUUID();
        List<Workout> workouts = List.of(new Workout());
        when(workoutRepository.findByUserId(userId)).thenReturn(workouts);
        List<Workout> result = workoutService.getWorkoutsByUserId(userId);
        assertEquals(1, result.size());
    }

    @Test
    void getWorkoutsByUserId_ShouldThrow_WhenEmpty() {
        UUID userId = UUID.randomUUID();
        when(workoutRepository.findByUserId(userId)).thenReturn(List.of());
        assertThrows(ApiException.class, () -> workoutService.getWorkoutsByUserId(userId));
    }

    @Test
    void createWorkout_ShouldCreateSuccessfully() {
        WorkoutRequest request = new WorkoutRequest();
        request.setName("Test");
        User user = new User();

        WorkoutExercise we = new WorkoutExercise();
        Exercise exercise = new Exercise();
        UUID exId = UUID.randomUUID();
        exercise.setId(exId);

        WorkoutExerciseRequest wer = new WorkoutExerciseRequest();
        wer.setExerciseId(exId);
        wer.setSets(3);
        wer.setReps(10);
        wer.setWeight(50);

        request.setExercises(List.of(wer));
        when(exerciseRepository.findById(exId)).thenReturn(Optional.of(exercise));
        when(workoutRepository.save(any(Workout.class))).thenAnswer(i -> i.getArgument(0));

        Workout result = workoutService.createWorkout(request, user);
        assertEquals("Test", result.getName());
        assertEquals(1, result.getWorkoutExercises().size());
    }

    @Test
    void createWorkout_ShouldThrow_WhenMissingName() {
        WorkoutRequest request = new WorkoutRequest();
        request.setName("");
        assertThrows(ApiException.class, () -> workoutService.createWorkout(request, new User()));
    }
}
