package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Comentario;
import co.edu.uniquindio.ProyectoFinalp3.models.Muro;
import co.edu.uniquindio.ProyectoFinalp3.repository.MuroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MuroServiceImpl implements MuroService {

    @Autowired
    private MuroRepository muroRepository;

    @Override
    public Muro crearMuro(Muro muro) {
        return muroRepository.save(muro);
    }

    @Override
    public Optional<Muro> obtenerMuroPorId(Long id) {
        return muroRepository.findById(id);
    }

    @Override
    public void incrementarLikes(Long id) {
        Muro muro = obtenerMuroPorId(id).orElseThrow(() -> new IllegalArgumentException("Muro no encontrado"));
        muro.incrementarLikes(); // Incrementa el like usando el método específico de Muro
        muroRepository.save(muro); // Guarda el cambio en la base de datos
    }

    @Override
    public void agregarComentario(Long idMuro, Comentario comentario) throws Exception {
        Muro muro = obtenerMuroPorId(idMuro).orElseThrow(() -> new IllegalArgumentException("Muro no encontrado"));
        muro.agregarComentario(comentario); // Usa el método en Muro para agregar el comentario
        muroRepository.save(muro); // Guarda el muro actualizado en la base de datos
    }

    @Override
    public void eliminarComentario(Long idMuro, Comentario comentario) {
        Muro muro = obtenerMuroPorId(idMuro).orElseThrow(() -> new IllegalArgumentException("Muro no encontrado"));
        muro.deleteComentario(comentario); // Elimina el comentario usando el método en Muro
        muroRepository.save(muro); // Guarda el cambio en la base de datos
    }
}

