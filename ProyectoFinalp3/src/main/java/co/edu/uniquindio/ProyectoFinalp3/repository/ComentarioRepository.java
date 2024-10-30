package co.edu.uniquindio.ProyectoFinalp3.repository;

import co.edu.uniquindio.ProyectoFinalp3.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByProductoId(Long productoId); // Método para obtener comentarios de un producto
}

