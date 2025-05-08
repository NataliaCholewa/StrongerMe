package strongerme.service;

import org.springframework.stereotype.Service;
import strongerme.exception.ApiException;
import strongerme.model.Routine;
import strongerme.repository.RoutineRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoutineService {

    private final RoutineRepository routineRepository;

    public RoutineService(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    public List<Routine> getAllRoutines() {
        return routineRepository.findAll();
    }

    public Routine getRoutineById(UUID id) {
        return routineRepository.findById(id).orElseThrow(() -> new ApiException("Routine not found", 404));
    }

    public List<Routine> getRoutinesByUserId(UUID userId) {
        return routineRepository.findByUserId(userId);
    }

    public Routine saveRoutine(Routine routine) {
        return routineRepository.save(routine);
    }

    public void deleteRoutine(UUID id) {
        if (!routineRepository.existsById(id)) {
            throw new ApiException("Routine not found", 404);
        }
        routineRepository.deleteById(id);
    }
}
