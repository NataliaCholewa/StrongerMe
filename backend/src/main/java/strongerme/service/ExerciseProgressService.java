package strongerme.service;

import org.springframework.stereotype.Service;
import strongerme.exception.ApiException;
import strongerme.model.ExerciseProgress;
import strongerme.repository.ExerciseProgressRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExerciseProgressService {

    private final ExerciseProgressRepository progressRepository;

    public ExerciseProgressService(ExerciseProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public List<ExerciseProgress> getAllProgress() {
        return progressRepository.findAll();
    }

    public ExerciseProgress getById(UUID id) {
        return progressRepository.findById(id)
                .orElseThrow(() -> new ApiException("Progress record not found", 404));
    }

    public List<ExerciseProgress> getByUserId(UUID userId) {
        List<ExerciseProgress> results = progressRepository.findByUserId(userId);
        if (results.isEmpty()) {
            throw new ApiException("No progress records for given user", 404);
        }
        return results;
    }

    public ExerciseProgress saveProgress(ExerciseProgress progress) {
        return progressRepository.save(progress);
    }

    public void deleteProgress(UUID id) {
        if (!progressRepository.existsById(id)) {
            throw new ApiException("Progress record not found", 404);
        }
        progressRepository.deleteById(id);
    }
}
