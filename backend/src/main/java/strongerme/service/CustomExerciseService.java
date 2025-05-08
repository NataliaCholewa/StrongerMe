package strongerme.service;

import org.springframework.stereotype.Service;
import strongerme.exception.ApiException;
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

    public CustomExercise getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Custom exercise not found", 404));
    }

    public List<CustomExercise> getAllByUserId(UUID userId) {
        return repository.findAllByUserId(userId);
    }

    public CustomExercise save(CustomExercise customExercise) {
        UUID userId = customExercise.getUser().getId();
        String name = customExercise.getName();

        Optional<CustomExercise> existing = repository.findByUserIdAndName(userId, name);
        if (existing.isPresent()) {
            throw new ApiException("Custom exercise with that name already exists", 400);
        }

        return repository.save(customExercise);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ApiException("Custom exercise not found", 404);
        }
        repository.deleteById(id);
    }
}
