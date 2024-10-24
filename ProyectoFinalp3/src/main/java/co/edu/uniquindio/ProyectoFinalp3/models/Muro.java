package co.edu.uniquindio.ProyectoFinalp3.models;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import java.io.Serializable;

import co.edu.uniquindio.ProyectoFinalp3.exceptions.ComentarioNoPermitidoException;

@Entity  
public class Muro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mensaje;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "publicacion_id")
    private List<Comentario> comentarios = new ArrayList<>();

    @Column(nullable = false)
    private int likes;

    public Muro(String mensaje, int likes) {
        this.mensaje = mensaje;
        this.comentarios = new ArrayList<>();
        this.likes = likes;
    }

    // Constructor sin parámetros requerido por JPA
    public Muro() {}

    // Getters y setters
    public String getMensaje() {
        return mensaje;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public int getLikes() {
        return likes;
    }

    public void deleteComentario(Comentario comentario) {
        comentarios.remove(comentario);
    }

    public void agregarComentario(Comentario comentario) throws ComentarioNoPermitidoException {
        if (comentario.getContenido().isEmpty()) {
            throw new ComentarioNoPermitidoException("El comentario no puede estar vacío.");
        }
        comentarios.add(comentario);
    }
}
