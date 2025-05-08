package strongerme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import strongerme.model.CustomExercise;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomExerciseRepository extends JpaRepository<CustomExercise, UUID> {

    Optional<CustomExercise> findByUserIdAndName(UUID userId, String name);

    List<CustomExercise> findAllByUserId(UUID userId);
}
