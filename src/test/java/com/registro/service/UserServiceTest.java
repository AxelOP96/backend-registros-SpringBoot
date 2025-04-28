package com.registro.service;

import com.registro.dto.LoginDTO;
import com.registro.dto.UserDTO;
import com.registro.exception.EmailAlreadyExistsException;
import com.registro.exception.InvalidPasswordException;
import com.registro.model.User;
import com.registro.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_successful() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Juan");
        userDTO.setLastName("Perez");
        userDTO.setEmail("juan@example.com");
        userDTO.setPassword("Password123");

        when(userRepository.findByEmail("juan@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("Password123")).thenReturn("encodedPassword");

        var response = userService.registerUser(userDTO);

        assertEquals("juan@example.com", response.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerUser_emailAlreadyExists() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Juan");
        userDTO.setLastName("Perez");
        userDTO.setEmail("juan@example.com");
        userDTO.setPassword("Password123");

        when(userRepository.findByEmail("juan@example.com")).thenReturn(Optional.of(new User()));

        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.registerUser(userDTO);
        });
    }

    @Test
    void loginUser_successful() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("juan@example.com");
        loginDTO.setPassword("Password123");

        User user = new User();
        user.setEmail("juan@example.com");
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail("juan@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("Password123", "encodedPassword")).thenReturn(true);

        var response = userService.loginUser(loginDTO);

        assertEquals("juan@example.com", response.getEmail());
    }

    @Test
    void loginUser_invalidPassword() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("juan@example.com");
        loginDTO.setPassword("wrongPassword");

        User user = new User();
        user.setEmail("juan@example.com");
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail("juan@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        assertThrows(InvalidPasswordException.class, () -> {
            userService.loginUser(loginDTO);
        });
    }

    @Test
    void getAllUsers_returnsList() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Juan");
        user1.setLastName("Perez");
        user1.setEmail("juan@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Ana");
        user2.setLastName("Lopez");
        user2.setEmail("ana@example.com");

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        var users = userService.getAllUsers();

        assertEquals(2, users.size());
    }
}
