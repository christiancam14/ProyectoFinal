package co.edu.uniquindio.ProyectoFinalp3.services;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Importa BCryptPasswordEncoder

import co.edu.uniquindio.ProyectoFinalp3.models.Administrador;
import co.edu.uniquindio.ProyectoFinalp3.models.AppUser;
import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import co.edu.uniquindio.ProyectoFinalp3.repository.AdministradorRepository;
import co.edu.uniquindio.ProyectoFinalp3.repository.UserRepository;
import co.edu.uniquindio.ProyectoFinalp3.repository.VendedorRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Inyecta el PasswordEncoder

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar en Administradores
        Optional<Administrador> administradorOpt = administradorRepository.findByEmail(username);
        if (administradorOpt.isPresent()) {
            Administrador administrador = administradorOpt.get();
            return new User(administrador.getEmail(), administrador.getContrasena(), getAuthorities("ADMIN"));
        }

        // Buscar en Vendedores
        Optional<Vendedor> vendedorOpt = vendedorRepository.findByCorreoElectronico(username);
        if (vendedorOpt.isPresent()) {
            Vendedor vendedor = vendedorOpt.get();
            return new User(vendedor.getCorreoElectronico(), vendedor.getContrasena(), getAuthorities("VENDEDOR"));
        }

        Optional<AppUser> usuarioOpt = userRepository.findByEmail(username);
        if (usuarioOpt.isPresent()) {
            AppUser user = usuarioOpt.get();
            return new User(user.getEmail(), user.getContrasena(), getAuthorities("VENDEDOR"));
        }
        throw new UsernameNotFoundException("Usuario no encontrado con el email: " + username);
    }

    public boolean validateUser(String email, String password) {
        Optional<AppUser> usuarioOpt = userRepository.findByEmail(email);
        if (!usuarioOpt.isPresent()) {
            return false; // Usuario no encontrado
        }

        AppUser usuario = usuarioOpt.get();

        // Verificar si la contraseña proporcionada coincide con la almacenada
        return passwordEncoder.matches(password, usuario.getContrasena());
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
    }

}
