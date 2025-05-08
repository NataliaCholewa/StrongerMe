package strongerme.service;

import org.springframework.stereotype.Service;
import strongerme.model.RoutineExercise;
import strongerme.repository.RoutineExerciseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoutineExerciseService {

    private final RoutineExerciseRepository routineExerciseRepository;

    public RoutineExerciseService(RoutineExerciseRepository repository) {
        this.routineExerciseRepository = repository;
    }

    public List<RoutineExercise> getAll() {
        return routineExerciseRepository.findAll();
    }

    public Optional<RoutineExercise> getById(UUID id) {
        return routineExerciseRepository.findById(id);
    }

    public List<RoutineExercise> getByRoutineId(UUID routineId) {
        return routineExerciseRepository.findByRoutineId(routineId);
    }

    public RoutineExercise save(RoutineExercise routineExercise) {
        return routineExerciseRepository.save(routineExercise);
    }

    public void delete(UUID id) {
        routineExerciseRepository.deleteById(id);
    }
}
