package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import co.edu.uniquindio.ProyectoFinalp3.models.Producto;

import java.util.List;
import java.util.Optional;

public interface VendedorService {

    Vendedor crearVendedor(Vendedor vendedor);

    Optional<Vendedor> obtenerVendedorPorId(Long id);

    Vendedor actualizarVendedor(Vendedor vendedor);

    void eliminarVendedor(Long id);

    List<Vendedor> listarVendedores();

    List<Producto> obtenerProductosDeVendedor(Long vendedorId);

    void agregarContactoAliado(Long vendedorId, Long aliadoId);

    void eliminarContactoAliado(Long vendedorId, Long aliadoId);
}

