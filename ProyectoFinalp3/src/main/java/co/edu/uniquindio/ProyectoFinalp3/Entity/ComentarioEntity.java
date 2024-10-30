package co.edu.uniquindio.ProyectoFinalp3.Entity;

import jakarta.persistence.*;
import java.io.Serializable;

import co.edu.uniquindio.ProyectoFinalp3.models.Producto;

@Entity
public class ComentarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contenido;

    // Relación con Producto
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

}
