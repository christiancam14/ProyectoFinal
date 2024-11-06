package co.edu.uniquindio.ProyectoFinalp3.repository;

import co.edu.uniquindio.ProyectoFinalp3.models.EstadoVenta;
import co.edu.uniquindio.ProyectoFinalp3.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Encuentra ventas por el estado
    List<Venta> findByEstadoVenta(EstadoVenta estadoVenta);

    // Encuentra ventas por el vendedor
    List<Venta> findByVendedorId(Long vendedorId);

    // Encuentra ventas por el producto
    List<Venta> findByProductoId(Long productoId);
}
