package strongerme.service;

import org.springframework.stereotype.Service;
import strongerme.model.RoutineExercise;
import strongerme.repository.RoutineExerciseRepository;
import strongerme.exception.ApiException;


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

    public RoutineExercise getById(UUID id) {
        return routineExerciseRepository.findById(id)
                .orElseThrow(() -> new ApiException("RoutineExercise not found", 404));
    }

    public List<RoutineExercise> getByRoutineId(UUID routineId) {
        List<RoutineExercise> list = routineExerciseRepository.findByRoutineId(routineId);
        if (list.isEmpty()) {
            throw new ApiException("No exercises found for this routine", 404);
        }
        return list;
    }

    public RoutineExercise save(RoutineExercise routineExercise) {
        return routineExerciseRepository.save(routineExercise);
    }

    public void delete(UUID id) {
        if (!routineExerciseRepository.existsById(id)) {
            throw new ApiException("RoutineExercise not found", 404);
        }
        routineExerciseRepository.deleteById(id);
    }
}
