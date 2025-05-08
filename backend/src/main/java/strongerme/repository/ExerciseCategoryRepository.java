package strongerme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strongerme.model.ExerciseCategory;

import java.util.UUID;

@Repository
public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory, UUID> {
}
