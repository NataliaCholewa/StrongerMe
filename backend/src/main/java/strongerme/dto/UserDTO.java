package strongerme.dto;

import strongerme.model.User;
import java.util.UUID;
import java.time.LocalDate;

public class UserDTO {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private Double weight;
    private Double height;
    private String role;
    private String createdAt;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthDate = user.getBirthDate();
        this.gender = user.getGender();
        this.weight = user.getWeight();
        this.height = user.getHeight();
        this.role = user.getRole().name();
        this.createdAt = user.getCreatedAt().toString();
    }

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getGender() { return gender; }
    public Double getWeight() { return weight; }
    public Double getHeight() { return height; }
    public String getRole() { return role; }
    public String getCreatedAt() { return createdAt; }
}
