package strongerme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strongerme.model.FavoriteExercise;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoriteExerciseRepository extends JpaRepository<FavoriteExercise, UUID> {
    List<FavoriteExercise> findByUserId(UUID userId);
}
