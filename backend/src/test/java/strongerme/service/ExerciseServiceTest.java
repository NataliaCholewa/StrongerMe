package strongerme.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strongerme.dto.ExerciseResponseDto;
import strongerme.exception.ApiException;
import strongerme.model.ExerciseCategory;
import strongerme.model.Exercise;
import strongerme.repository.ExerciseRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExerciseServiceTest {

    private ExerciseRepository exerciseRepository;
    private ExerciseService exerciseService;

    @BeforeEach
    void setUp() {
        exerciseRepository = mock(ExerciseRepository.class);
        exerciseService = new ExerciseService(exerciseRepository);
    }

    @Test
    void getAllWithCategory_returnsList() {
        List<Exercise> exercises = List.of(new Exercise(), new Exercise());
        when(exerciseRepository.findAllWithCategory()).thenReturn(exercises);

        List<Exercise> result = exerciseService.getAllWithCategory();

        assertEquals(2, result.size());
        verify(exerciseRepository).findAllWithCategory();
    }

    @Test
    void createExercise_successful() {
        Exercise exercise = new Exercise();
        exercise.setName("Squat");

        when(exerciseRepository.findByName("Squat")).thenReturn(Optional.empty());
        when(exerciseRepository.save(exercise)).thenReturn(exercise);

        Exercise result = exerciseService.createExercise(exercise);

        assertEquals("Squat", result.getName());
        verify(exerciseRepository).save(exercise);
    }

    @Test
    void createExercise_nameAlreadyExists_throwsException() {
        Exercise existing = new Exercise();
        existing.setName("Push-up");

        when(exerciseRepository.findByName("Push-up")).thenReturn(Optional.of(existing));

        Exercise newExercise = new Exercise();
        newExercise.setName("Push-up");

        ApiException exception = assertThrows(ApiException.class, () -> exerciseService.createExercise(newExercise));
        assertEquals("Exercise with that name already exists", exception.getMessage());
        assertEquals(400, exception.getStatusCode());
    }

    @Test
    void getByName_found() {
        Exercise exercise = new Exercise();
        exercise.setName("Deadlift");

        when(exerciseRepository.findByName("Deadlift")).thenReturn(Optional.of(exercise));

        Exercise result = exerciseService.getByName("Deadlift");

        assertEquals("Deadlift", result.getName());
    }

    @Test
    void getByName_notFound_throwsException() {
        when(exerciseRepository.findByName("Bench Press")).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> exerciseService.getByName("Bench Press"));
        assertEquals("Exercise not found", exception.getMessage());
        assertEquals(404, exception.getStatusCode());
    }

    @Test
    void getById_found() {
        UUID id = UUID.randomUUID();
        Exercise exercise = new Exercise();
        exercise.setId(id);

        when(exerciseRepository.findById(id)).thenReturn(Optional.of(exercise));

        Exercise result = exerciseService.getById(id);

        assertEquals(id, result.getId());
    }

    @Test
    void getById_notFound_throwsException() {
        UUID id = UUID.randomUUID();

        when(exerciseRepository.findById(id)).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> exerciseService.getById(id));
        assertEquals("Exercise not found", exception.getMessage());
    }

    @Test
    void deleteById_successful() {
        UUID id = UUID.randomUUID();
        when(exerciseRepository.existsById(id)).thenReturn(true);

        exerciseService.deleteById(id);

        verify(exerciseRepository).deleteById(id);
    }

    @Test
    void deleteById_notFound_throwsException() {
        UUID id = UUID.randomUUID();
        when(exerciseRepository.existsById(id)).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class, () -> exerciseService.deleteById(id));
        assertEquals("Exercise not found", exception.getMessage());
    }

    @Test
    void getAllAsDto_successful() {
        Exercise ex = new Exercise();
        ex.setId(UUID.randomUUID());
        ex.setName("Pull-up");
        ex.setDescription("Upper body strength");
        ex.setImageUrl("http://example.com/pullup.png");
        ex.setUnilateral(false);

        ExerciseCategory category = new ExerciseCategory();
        category.setName("Back");
        ex.setCategory(category);

        when(exerciseRepository.findAllWithCategory()).thenReturn(List.of(ex));

        List<ExerciseResponseDto> result = exerciseService.getAllAsDto();

        assertEquals(1, result.size());
        ExerciseResponseDto dto = result.get(0);
        assertEquals("Pull-up", dto.getName());
        assertEquals("Back", dto.getCategoryName());
    }
}
