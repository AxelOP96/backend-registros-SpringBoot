package com.registro.controller;

import com.registro.dto.UserDTO;
import com.registro.dto.LoginDTO;
import com.registro.dto.UserResponseDTO;
import com.registro.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "Endpoints para registro, login y usuarios")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Registrar un nuevo usuario")
    public UserResponseDTO registerUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión de usuario")
    public UserResponseDTO loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        return userService.loginUser(loginDTO);
    }

    @GetMapping("/users")
    @Operation(summary = "Obtener lista de todos los usuarios")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
