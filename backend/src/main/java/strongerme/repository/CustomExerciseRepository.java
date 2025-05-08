package strongerme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strongerme.model.CustomExercise;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomExerciseRepository extends JpaRepository<CustomExercise, UUID> {
    List<CustomExercise> findByUserId(UUID userId);
}
