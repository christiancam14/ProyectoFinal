package co.edu.uniquindio.ProyectoFinalp3.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contenido;

    // Relación con Producto
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Constructor vacío requerido por JPA
    public Comentario() {}

    // Constructor con parámetros
    public Comentario(String contenido, Producto producto) {
        this.contenido = contenido;
        this.producto = producto;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
