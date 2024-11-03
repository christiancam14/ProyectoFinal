package co.edu.uniquindio.ProyectoFinalp3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniquindio.ProyectoFinalp3.Config.JwtUtil;
import co.edu.uniquindio.ProyectoFinalp3.dto.LoginDto;
import co.edu.uniquindio.ProyectoFinalp3.dto.RegisterDto;
import co.edu.uniquindio.ProyectoFinalp3.models.AppUser;
import co.edu.uniquindio.ProyectoFinalp3.services.CustomUserDetailsService;
import co.edu.uniquindio.ProyectoFinalp3.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getContrasena());

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            if (authentication.isAuthenticated()) {
                String jwt = jwtUtil.create(loginDto.getEmail());
                return ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                        .body("Login successful"); // Puedes retornar un mensaje o el JWT aquí.
            }
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password"); // Mensaje de error claro.
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing your request"); // Manejo genérico de errores.
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed"); // Si no se autenticó.
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateUser(@RequestBody LoginDto loginDto) {
        boolean isValid = userDetailsService.validateUser(loginDto.getEmail(), loginDto.getContrasena());
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
        if (userService.existsByEmail(registerDto.getEmail())) {
            return ResponseEntity.badRequest().body("El email ya está en uso.");
        }

        AppUser newUser = new AppUser();
        newUser.setNombre(registerDto.getNombre());
        newUser.setEmail(registerDto.getEmail());
        newUser.setContrasena(registerDto.getContrasena());

        userService.registerUser(newUser);
        return ResponseEntity.ok("Usuario registrado con éxito.");
    }
}
