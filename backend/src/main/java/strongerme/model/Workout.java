package strongerme.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "workouts")
public class Workout {
    
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String description;
    private LocalDateTime performedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "workout")
    private List<WorkoutExercise> workoutExercises;

///// gettery i settery skonzyc
    public Workout() {}

    public Workout(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
