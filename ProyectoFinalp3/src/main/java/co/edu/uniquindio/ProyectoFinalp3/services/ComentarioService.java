package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Comentario;
import java.util.List;
import java.util.Optional;

public interface ComentarioService {
    Comentario crearComentario(Comentario comentario);
    Optional<Comentario> obtenerComentarioPorId(Long id);
    List<Comentario> listarComentariosPorProducto(Long productoId);
    Comentario actualizarComentario(Comentario comentario);
    void eliminarComentario(Long id);
}

