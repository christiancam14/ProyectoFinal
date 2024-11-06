package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.models.Estado;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto crearProducto(Producto producto);

    Optional<Producto> obtenerProductoPorId(Long id);

    List<Producto> listarProductos();

    Producto actualizarProducto(Producto producto);

    void eliminarProducto(Long id);

    List<Producto> obtenerProductosPorVendedor(Long vendedorId);

    List<Producto> obtenerProductosPorEstado(Estado estado);
}

