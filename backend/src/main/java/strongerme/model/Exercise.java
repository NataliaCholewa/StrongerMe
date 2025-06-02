package strongerme.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "exercises")
public class Exercise {
    
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;  // tekstowy opis techniki
    private String imageUrl;     // ew link do zdjecia 

    @Column(nullable = false)
    private boolean isUnilateral = false;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private ExerciseCategory category;

    @OneToMany(mappedBy = "exercise")
    private List<WorkoutExercise> workoutExercises;

    @OneToMany(mappedBy = "exercise")
    private List<RoutineExercise> routineExercises;


    public Exercise() {} 
    public Exercise(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isUnilateral() {
        return isUnilateral;
    }

    public void setUnilateral(boolean unilateral) {
        isUnilateral = unilateral;
    }

    public ExerciseCategory getCategory() {
        return category;
    }

    public void setCategory(ExerciseCategory category) {
        this.category = category;
    }

    public List<WorkoutExercise> getWorkoutExercises() {
        return workoutExercises;
    }

    public void setWorkoutExercises(List<WorkoutExercise> workoutExercises) {
        this.workoutExercises = workoutExercises;
    }

    public List<RoutineExercise> getRoutineExercises() {
        return routineExercises;
    }

    public void setRoutineExercises(List<RoutineExercise> routineExercises) {
        this.routineExercises = routineExercises;
    }


    

}
