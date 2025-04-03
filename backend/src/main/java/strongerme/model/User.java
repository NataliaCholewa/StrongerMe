package strongerme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private Double weight;
    private Double height;

   @Column(nullable = false)
   private String role = "user";

   @Column(nullable = false)
   private LocalDate createdAt = LocalDate.now();

    @OneToMany(mappedBy = "user")
    private List<Workout> workouts;

    @OneToMany(mappedBy = "user")
    private List<Routine> routines;

    @OneToMany(mappedBy = "user")                 
    private List<FavoriteExercise> favouriteExercises;


    public User() {}

    public User(UUID id, String email, String password) {          // dopisac reszte
        this.id = id;           
        this.email = email;
        this.password = password;
    }
 // jak sie automaytycznie generuje gettery i settery??
    public UUID getId() { return id; }                             // dopisac gettery i settery
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setId(UUID id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
