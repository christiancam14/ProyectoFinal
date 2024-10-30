package co.edu.uniquindio.ProyectoFinalp3.repository;

import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Encuentra productos por estado (ej. PUBLICADO, AGOTADO, VENDIDO)
    List<Producto> findByEstado(Estado estado);

    // Encuentra productos de un vendedor específico
    List<Producto> findByVendedorId(Long vendedorId);

}

