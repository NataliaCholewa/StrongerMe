package strongerme.service;

import org.springframework.stereotype.Service;

import strongerme.exception.ApiException;
import strongerme.model.Exercise;
import strongerme.model.User;
import strongerme.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new ApiException("User with that email already exists", 400);
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ApiException("User not found", 404));

    }

    public User getById(UUID id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ApiException("User not found", 404));
    }

    public void deleteById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ApiException("User not found", 404);
        }
        userRepository.deleteById(id);
    }
    
}
