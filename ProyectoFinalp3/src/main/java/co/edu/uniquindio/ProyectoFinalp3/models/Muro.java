package co.edu.uniquindio.ProyectoFinalp3.models;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.ProyectoFinalp3.exceptions.ComentarioNoPermitidoException;

public class Muro {
    private String mensaje;
    private List<Comentario> comentarios;
    private int likes;

    public Muro(String mensaje, int likes) {
        this.mensaje = mensaje;
        this.comentarios = new ArrayList<>();
        this.likes = likes;
    }

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
