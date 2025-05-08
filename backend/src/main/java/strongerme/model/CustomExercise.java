package strongerme.model;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "custom_exercises")
public class CustomExercise {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String imageUrl; // np. link do zdjęcia z formą

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // kto stworzył to ćwiczenie

    public CustomExercise() {}

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
