package strongerme.service;

import org.springframework.stereotype.Service;
import strongerme.model.CustomExercise;
import strongerme.repository.CustomExerciseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomExerciseService {

    private final CustomExerciseRepository repository;

    public CustomExerciseService(CustomExerciseRepository repository) {
        this.repository = repository;
    }

    public List<CustomExercise> getAll() {
        return repository.findAll();
    }

    public Optional<CustomExercise> getById(UUID id) {
        return repository.findById(id);
    }

    public List<CustomExercise> getByUserId(UUID userId) {
        return repository.findByUserId(userId);
    }

    public CustomExercise save(CustomExercise customExercise) {
        return repository.save(customExercise);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
