package strongerme.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "routine_exercises")
public class RoutineExercise {
    
    @Id
    @GeneratedValue
    private UUID id;

    private int sets;
    private int reps;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    //gettrery i se

}
