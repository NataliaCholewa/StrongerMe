package strongerme.dto;

import java.util.UUID;

public class ExerciseDto {

    private String name;
    private String description;
    private String imageUrl;
    private boolean isUnilateral;
    private UUID categoryId;

    public ExerciseDto() {}

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

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
