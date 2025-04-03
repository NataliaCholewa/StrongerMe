package strongerme.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "routines")
public class Routine {
    
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String description;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "routine")
    private List<RoutineExercise> routineExercises;

    // get set
}
