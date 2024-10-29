package co.edu.uniquindio.ProyectoFinalp3.dto;

public class LoginRequest {
    private String email;
    private String contrasena;

    // Constructor vacío
    public LoginRequest() {
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
