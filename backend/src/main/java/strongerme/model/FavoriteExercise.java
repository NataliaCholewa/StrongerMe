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
    // gettery
}
