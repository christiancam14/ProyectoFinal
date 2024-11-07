package co.edu.uniquindio.ProyectoFinalp3.controllers;

import co.edu.uniquindio.ProyectoFinalp3.models.User;
import co.edu.uniquindio.ProyectoFinalp3.services.JwtService;
import co.edu.uniquindio.ProyectoFinalp3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    // Login: Autenticación del usuario
    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        User user = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            return jwtService.generateToken(user.getEmail());
        }
        throw new RuntimeException("Invalid credentials");
    }

    // Register: Registro de un nuevo usuario
    @PostMapping("/register")
    public String register(@RequestBody User registerRequest) {
        // Verifica si el correo electrónico ya está registrado
        if (userService.emailExists(registerRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Crea el usuario (asegúrate de que la contraseña esté codificada)
        User newUser = userService.createUser(registerRequest);
        
        // Genera el JWT para el nuevo usuario
        return jwtService.generateToken(newUser.getEmail());
    }
}