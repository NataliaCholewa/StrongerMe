package strongerme.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "exercises")
public class Exercise {
    
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private boolean isUnilateral = false;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExerciseCategory category;

    @OneToMany(mappedBy = "exercise")
    private List<WorkoutExercise> workoutExercises;

    @OneToMany(mappedBy = "exercise")
    private List<RoutineExercise> routineExercises;

    //// gettery i settery

}
