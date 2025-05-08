package strongerme.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "routine_exercises")
public class RoutineExercise {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private int sets;

    @Column(nullable = false)
    private int reps;

    @Column(nullable = false)
    private double weight;

    @ManyToOne
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    public RoutineExercise() {}

    public RoutineExercise(int sets, int reps, double weight, Routine routine, Exercise exercise) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.routine = routine;
        this.exercise = exercise;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public int getSets() { return sets; }
    public void setSets(int sets) { this.sets = sets; }

    public int getReps() { return reps; }
    public void setReps(int reps) { this.reps = reps; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public Routine getRoutine() { return routine; }
    public void setRoutine(Routine routine) { this.routine = routine; }

    public Exercise getExercise() { return exercise; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }
}
