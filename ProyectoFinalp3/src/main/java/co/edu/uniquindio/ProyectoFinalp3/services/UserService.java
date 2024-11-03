// src/main/java/co/edu/uniquindio/ProyectoFinalp3/services/UserService.java
package co.edu.uniquindio.ProyectoFinalp3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.uniquindio.ProyectoFinalp3.models.AppUser;
import co.edu.uniquindio.ProyectoFinalp3.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser registerUser(AppUser user) {
        // Encripta la contraseña antes de guardar el usuario
        user.setContrasena(passwordEncoder.encode(user.getContrasena()));
        return userRepository.save(user); // Guarda el usuario en la base de datos
    }

    public boolean existsByEmail(String email) {
        // Verifica si el email ya está registrado
        return userRepository.existsByEmail(email);
    }
}