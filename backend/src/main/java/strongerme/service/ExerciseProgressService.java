package strongerme.service;

import org.springframework.stereotype.Service;
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

    public Optional<ExerciseProgress> getById(UUID id) {
        return progressRepository.findById(id);
    }

    public List<ExerciseProgress> getByUserId(UUID userId) {
        return progressRepository.findByUserId(userId);
    }

    public ExerciseProgress saveProgress(ExerciseProgress progress) {
        return progressRepository.save(progress);
    }

    public void deleteProgress(UUID id) {
        progressRepository.deleteById(id);
    }
}
