package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;

import java.util.List;
import java.util.Optional;

public interface VendedorService {

    Optional<Vendedor> obtenerVendedorPorId(Long id);

    Vendedor crearVendedor(Vendedor vendedor);

    String publicarProducto(Long idVendedor, Producto producto);

    List<Producto> obtenerProductosDeVendedor(Long idVendedor);

    String agregarContactoAliado(Long idVendedor, Vendedor aliado);

    String eliminarContactoAliado(Long idVendedor, Vendedor aliado);

    List<Producto> obtenerTop10ProductosMasVendidos(Long idVendedor);
}

