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

    public WorkoutExercise() {
    }

    public WorkoutExercise(int sets, int reps, double weight, boolean isPr, Workout workout, Exercise exercise) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.isPr = isPr;
        this.workout = workout;
        this.exercise = exercise;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isPr() {
        return isPr;
    }

    public void setPr(boolean pr) {
        isPr = pr;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
