package strongerme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.nio.file.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/uploads")
public class UploadController {

    private final String uploadDir = "uploads/";

    @PostMapping("/exercise-image")
    public ResponseEntity<?> uploadExerciseImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("No file provided.");
            }

            String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + filename);

            Files.createDirectories(path.getParent());

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = "/uploads/" + filename;
            return ResponseEntity.ok().body(Map.of("imageUrl", imageUrl));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Upload failed: " + e.getMessage());
        }
    }
}
