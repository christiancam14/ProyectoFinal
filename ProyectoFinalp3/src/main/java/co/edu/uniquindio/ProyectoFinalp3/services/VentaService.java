package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Venta;
import co.edu.uniquindio.ProyectoFinalp3.models.EstadoVenta;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.EstadoVentaInvalidoException;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.ProductoSinUnidadesDisponiblesException;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.VentaNoValidaException;

import java.util.List;
import java.util.Optional;

public interface VentaService {

    Venta crearVenta(Venta venta) throws ProductoSinUnidadesDisponiblesException, VentaNoValidaException;
    Optional<Venta> obtenerVentaPorId(Long id);
    List<Venta> listarVentas();
    List<Venta> listarVentasPorEstado(EstadoVenta estado);
    Venta actualizarEstadoVenta(Long id, EstadoVenta nuevoEstado) throws EstadoVentaInvalidoException;
    void eliminarVenta(Long id);
}


