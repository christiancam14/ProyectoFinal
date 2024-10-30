package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Comentario;
import co.edu.uniquindio.ProyectoFinalp3.models.Muro;

import java.util.Optional;

public interface MuroService {

    // Crea un nuevo muro
    Muro crearMuro(Muro muro);

    // Obtiene un muro específico por su ID
    Optional<Muro> obtenerMuroPorId(Long id);

    // Incrementa los likes de un muro específico
    void incrementarLikes(Long id);

    // Agrega un comentario al muro con el ID especificado
    void agregarComentario(Long idMuro, Comentario comentario) throws Exception;

    // Elimina un comentario del muro con el ID especificado
    void eliminarComentario(Long idMuro, Comentario comentario);
}

