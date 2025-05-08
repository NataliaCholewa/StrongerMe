package strongerme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import strongerme.model.RoutineExercise;

import java.util.List;
import java.util.UUID;

public interface RoutineExerciseRepository extends JpaRepository<RoutineExercise, UUID> {
    List<RoutineExercise> findByRoutineId(UUID routineId);
}
