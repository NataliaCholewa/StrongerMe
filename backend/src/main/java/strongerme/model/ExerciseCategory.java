package strongerme.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "exercise_category")
public class ExerciseCategory {
    
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Exercise> exercises;

    // gettery i settery
}
