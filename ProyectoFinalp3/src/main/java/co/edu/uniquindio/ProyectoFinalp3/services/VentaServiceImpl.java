package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Venta;
import co.edu.uniquindio.ProyectoFinalp3.models.EstadoVenta;
import co.edu.uniquindio.ProyectoFinalp3.repository.VentaRepository;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.EstadoVentaInvalidoException;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.ProductoSinUnidadesDisponiblesException;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.VentaNoValidaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public Venta crearVenta(Venta venta) throws ProductoSinUnidadesDisponiblesException, VentaNoValidaException {
        // Validar si hay stock suficiente para realizar la venta
        if (venta.getProducto().getUnidadesDisponibles() < venta.getCantidad()) {
            throw new ProductoSinUnidadesDisponiblesException("Stock insuficiente para el producto: " + venta.getProducto().getNombre());
        }

        // Realizar el ajuste en el inventario del producto
        venta.getProducto().setUnidadesDisponibles(venta.getProducto().getUnidadesDisponibles() - venta.getCantidad());
        
        return ventaRepository.save(venta);
    }

    @Override
    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public List<Venta> listarVentasPorEstado(EstadoVenta estado) {
        return ventaRepository.findByEstadoVenta(estado);
    }

    @Override
    public Venta actualizarEstadoVenta(Long id, EstadoVenta nuevoEstado) throws EstadoVentaInvalidoException {
        Optional<Venta> ventaOpt = ventaRepository.findById(id);
        if (ventaOpt.isPresent()) {
            Venta venta = ventaOpt.get();
            venta.cambiarEstadoVenta(nuevoEstado);
            return ventaRepository.save(venta);
        } else {
            throw new EstadoVentaInvalidoException("Venta no encontrada para actualizar el estado.");
        }
    }

    @Override
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}


