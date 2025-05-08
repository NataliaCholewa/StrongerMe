package strongerme.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "favorite_exercises")
public class FavoriteExercise {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public FavoriteExercise() {}

    public FavoriteExercise(User user, Exercise exercise) {
        this.user = user;
        this.exercise = exercise;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
