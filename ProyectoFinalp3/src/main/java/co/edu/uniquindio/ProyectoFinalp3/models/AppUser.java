// src/main/java/co/edu/uniquindio/ProyectoFinalp3/models/User.java
package co.edu.uniquindio.ProyectoFinalp3.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String contrasena;

    public AppUser(String correoElectronico, String contrasena2, Collection<? extends GrantedAuthority> authorities) {
        // TODO Auto-generated constructor stub
    }

    public AppUser() {
        // TODO Auto-generated constructor stub
    }

    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter y Setter para contrasena
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}