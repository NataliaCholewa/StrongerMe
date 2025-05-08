package strongerme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import strongerme.model.Routine;

import java.util.List;
import java.util.UUID;

public interface RoutineRepository extends JpaRepository<Routine, UUID> {
    List<Routine> findByUserId(UUID userId);
}
