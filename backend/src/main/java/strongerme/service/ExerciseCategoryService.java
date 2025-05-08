package strongerme.service;

import org.springframework.stereotype.Service;
import strongerme.model.ExerciseCategory;
import strongerme.repository.ExerciseCategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExerciseCategoryService {

    private final ExerciseCategoryRepository repository;

    public ExerciseCategoryService(ExerciseCategoryRepository repository) {
        this.repository = repository;
    }

    public List<ExerciseCategory> getAll() {
        return repository.findAll();
    }

    public Optional<ExerciseCategory> getById(UUID id) {
        return repository.findById(id);
    }
}
