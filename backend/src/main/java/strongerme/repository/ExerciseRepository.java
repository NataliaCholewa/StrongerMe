package strongerme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import strongerme.model.Exercise;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
    @Query("SELECT e FROM Exercise e JOIN FETCH e.category")
    List<Exercise> findAllWithCategory();

    Optional<Exercise> findByName(String name);
}
