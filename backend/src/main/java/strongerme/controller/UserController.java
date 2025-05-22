package strongerme.controller;

import strongerme.model.Exercise;
import strongerme.model.User;
import strongerme.repository.UserRepository;
import strongerme.service.ExerciseService;
import strongerme.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String value) {
    User user = userService.getByEmail(value); 
    return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
    User saved = userService.createUser(user);
    return ResponseEntity.ok(saved);
    }
}


