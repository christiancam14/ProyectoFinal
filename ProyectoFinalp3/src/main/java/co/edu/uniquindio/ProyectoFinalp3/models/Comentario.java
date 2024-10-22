package co.edu.uniquindio.ProyectoFinalp3.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.io.Serializable;

class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaYhora;

    @Column(nullable = false, length = 1000)
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
