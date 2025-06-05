package strongerme.repository;

import strongerme.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, UUID> {

    List<Workout> findByUserId(UUID userId);

    @Query("SELECT COUNT(w) FROM Workout w WHERE w.user.id = :userId")
long countByUserId(UUID userId);

@Query("SELECT COALESCE(SUM(we.sets * we.reps * we.weight), 0) " +
       "FROM WorkoutExercise we WHERE we.workout.user.id = :userId")
BigDecimal calculateTotalVolume(UUID userId);

@Query("SELECT COUNT(w) FROM Workout w " +
       "WHERE w.user.id = :userId AND w.performedAt >= :start AND w.performedAt <= :end")
int countWorkoutsInWeek(UUID userId, LocalDateTime start, LocalDateTime end);

}


