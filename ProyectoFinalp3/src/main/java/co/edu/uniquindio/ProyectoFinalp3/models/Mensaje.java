package co.edu.uniquindio.ProyectoFinalp3.models;

import java.time.LocalDateTime;

public class Mensaje {

    private String contenido;
    private Long remitenteId;
    private Long destinatarioId;
    private LocalDateTime fechaEnvio;

    // Constructor vacío
    public Mensaje() {}

    // Constructor con parámetros
    public Mensaje(String contenido, Long remitenteId, Long destinatarioId) {
        this.contenido = contenido;
        this.remitenteId = remitenteId;
        this.destinatarioId = destinatarioId;
        this.fechaEnvio = LocalDateTime.now();
    }

    // Getters y Setters
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Long getRemitenteId() {
        return remitenteId;
    }

    public void setRemitenteId(Long remitenteId) {
        this.remitenteId = remitenteId;
    }

    public Long getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Long destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}

