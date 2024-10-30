package co.edu.uniquindio.ProyectoFinalp3.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class MuroEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mensaje;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "publicacion_id")
    private List<ComentarioEntity> comentarios = new ArrayList<>();

    @Column(nullable = false)
    private int likes;
}
