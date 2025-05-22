package strongerme.service;

import org.springframework.stereotype.Service;

import strongerme.exception.ApiException;
import strongerme.model.Exercise;
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

    public ExerciseCategory getById(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new ApiException("Category not found", 404));
    }

    /*
     * 
     * public ExerciseCategory save(ExerciseCategory category) {
        Optional<ExerciseCategory> existing = repository.findByName(category.getName());
        if (existing.isPresent()) {
            throw new ApiException("Category with that name already exists", 400);
        }
        return repository.save(category);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ApiException("Category not found", 404);
        }
        repository.deleteById(id);
    }
     * 
     */

}
