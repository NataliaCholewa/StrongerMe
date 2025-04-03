package strongerme.model;

import jakarta.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "workout_exercises")
public class WorkoutExercise {

    @Id
    @GeneratedValue
    private UUID id;

    private int sets;
    private int reps;
    private double weight;

    @Column(nullable = false)
    private boolean isPr = false;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    /// gettery i settery

    
}
