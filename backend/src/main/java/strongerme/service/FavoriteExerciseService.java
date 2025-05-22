package strongerme.service;

import org.springframework.stereotype.Service;

import strongerme.exception.ApiException;
import strongerme.model.FavoriteExercise;
import strongerme.repository.FavoriteExerciseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FavoriteExerciseService {

    private final FavoriteExerciseRepository repository;

    public FavoriteExerciseService(FavoriteExerciseRepository repository) {
        this.repository = repository;
    }

    public List<FavoriteExercise> getAll() {
        return repository.findAll();
    }

    public FavoriteExercise getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Favorite exercise not found", 404));
    }

    public List<FavoriteExercise> getByUserId(UUID userId) {
        return repository.findByUserId(userId);
    }

    public FavoriteExercise save(FavoriteExercise favoriteExercise) {
        return repository.save(favoriteExercise);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ApiException("Favorite exercise not found", 404);
        }
        repository.deleteById(id);
    }
}
