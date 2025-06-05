package strongerme.dto;

import java.util.UUID;

public class ExerciseResponseDto {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private boolean isUnilateral;
    private String categoryName;

    // Gettery i settery
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
