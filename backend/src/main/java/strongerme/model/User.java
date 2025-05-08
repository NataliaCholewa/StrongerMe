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
    private List<FavoriteExercise> favoriteExercises;


    public User() {}

    public User(UUID id, String email, String password) {          
        this.id = id;           
        this.email = email;
        this.password = password;
    }
 
    public UUID getId() { return id; }                             
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getGender() { return gender; }
    public Double getWeight() { return weight; }
    public Double getHeight() { return height; }
    public String getRole() { return role; }
    public LocalDate getCreatedAt() { return createdAt; }
    public List<Workout> getWorkouts() { return workouts; }
    public List<Routine> getRoutines() { return routines; }
    public List<FavoriteExercise> getFavoriteExercises() { return favoriteExercises; }


    public void setId(UUID id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setGender(String gender) { this.gender = gender; }
    public void setWeight(Double weight) { this.weight = weight; }
    public void setHeight(Double height) { this.height = height; }
    public void setRole(String role) { this.role = role; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
    public void setWorkouts(List<Workout> workouts) { this.workouts = workouts; }
    public void setRoutines(List<Routine> routines) { this.routines = routines; }
    public void setFavoriteExercises(List<FavoriteExercise> favoriteExercises) { this.favoriteExercises = favoriteExercises; }

    
}
