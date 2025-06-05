package strongerme.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import strongerme.dto.UserDTO;
import strongerme.exception.ApiException;
import strongerme.exception.ApiErrorResponse;
import strongerme.model.User;
import strongerme.repository.UserRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void createUser_successful() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(user, result);
        verify(userRepository).save(user);
    }

    @Test
    void createUser_userAlreadyExists() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ApiException exception = assertThrows(ApiException.class, () -> userService.createUser(user));
        assertEquals("User with that email already exists", exception.getMessage());
        assertEquals(400, exception.getStatusCode());
    }

    @Test
    void getAllUsers_returnsList() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
    }

    @Test
    void getByEmail_userExists() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User result = userService.getByEmail("test@example.com");
        assertEquals(user, result);
    }

    @Test
    void getByEmail_userNotFound() {
        when(userRepository.findByEmail("missing@example.com")).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> userService.getByEmail("missing@example.com"));
        assertEquals("User not found", exception.getMessage());
        assertEquals(404, exception.getStatusCode());
    }

    @Test
    void getById_userExists() {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = userService.getById(id);
        assertEquals(user, result);
    }

    @Test
    void getById_userNotFound() {
        UUID id = UUID.randomUUID();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> userService.getById(id));
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void deleteById_successful() {
        UUID id = UUID.randomUUID();
        when(userRepository.existsById(id)).thenReturn(true);

        userService.deleteById(id);

        verify(userRepository).deleteById(id);
    }

    @Test
    void deleteById_userNotFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.existsById(id)).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class, () -> userService.deleteById(id));
        assertEquals("User not found", exception.getMessage());
    }
}
