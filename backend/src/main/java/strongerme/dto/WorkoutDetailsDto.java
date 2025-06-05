package strongerme.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class WorkoutDetailsDto {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime performedAt;
    private List<ExerciseEntry> workoutExercises;

    public static class ExerciseEntry {
        private UUID id;
        private String name;
        private int sets;
        private int reps;
        private double weight;

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getSets() { return sets; }
        public void setSets(int sets) { this.sets = sets; }

        public int getReps() { return reps; }
        public void setReps(int reps) { this.reps = reps; }

        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = weight; }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; } 

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getPerformedAt() { return performedAt; }
    public void setPerformedAt(LocalDateTime performedAt) { this.performedAt = performedAt; }

    public List<ExerciseEntry> getWorkoutExercises() { return workoutExercises; }
    public void setWorkoutExercises(List<ExerciseEntry> workoutExercises) {
        this.workoutExercises = workoutExercises;
    }
}
