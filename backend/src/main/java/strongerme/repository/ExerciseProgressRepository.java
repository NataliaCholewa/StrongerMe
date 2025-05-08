package strongerme.repository;

import strongerme.model.ExerciseProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ExerciseProgressRepository extends JpaRepository<ExerciseProgress, UUID> {
    List<ExerciseProgress> findByUserId(UUID userId);
    List<ExerciseProgress> findByExerciseId(UUID exerciseId);
}
