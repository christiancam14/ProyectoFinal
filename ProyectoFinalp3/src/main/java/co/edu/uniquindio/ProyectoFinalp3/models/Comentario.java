package co.edu.uniquindio.ProyectoFinalp3.models;

import java.time.LocalDateTime;

class Comentario {
    private LocalDateTime fechaYhora;
    private String contenido;

    public Comentario(String contenido) {
        this.fechaYhora = LocalDateTime.now();
        this.contenido = contenido;
    }
    public LocalDateTime getFechaYhora() {
        return fechaYhora;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
