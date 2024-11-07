package co.edu.uniquindio.ProyectoFinalp3.controllers;

import co.edu.uniquindio.ProyectoFinalp3.models.Venta;
import co.edu.uniquindio.ProyectoFinalp3.models.EstadoVenta;
import co.edu.uniquindio.ProyectoFinalp3.services.VentaService;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.EstadoVentaInvalidoException;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.ProductoSinUnidadesDisponiblesException;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.VentaNoValidaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Crear una nueva venta con manejo de excepciones
    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody Venta venta) {
        try {
            Venta nuevaVenta = ventaService.crearVenta(venta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
        } catch (ProductoSinUnidadesDisponiblesException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Producto sin unidades disponibles.");
        } catch (VentaNoValidaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Venta no válida. " + e.getMessage());
        }
    }

    // Obtener una venta por su ID con mensaje claro
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable Long id) {
    Optional<Venta> ventaOpt = ventaService.obtenerVentaPorId(id);
    return ventaOpt
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada con ID: " + id));
}


    // Listar todas las ventas con mensaje en caso de lista vacía
    @GetMapping
    public ResponseEntity<?> listarVentas() {
        List<Venta> ventas = ventaService.listarVentas();
        if (ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay ventas registradas.");
        }
        return ResponseEntity.ok(ventas);
    }

    // Listar ventas por estado con mensaje claro en caso de lista vacía
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> listarVentasPorEstado(@PathVariable EstadoVenta estado) {
        List<Venta> ventas = ventaService.listarVentasPorEstado(estado);
        if (ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron ventas con el estado: " + estado);
        }
        return ResponseEntity.ok(ventas);
    }

    // Actualizar el estado de una venta con manejo de excepción
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstadoVenta(@PathVariable Long id, @RequestParam EstadoVenta nuevoEstado) {
        try {
            Venta ventaActualizada = ventaService.actualizarEstadoVenta(id, nuevoEstado);
            return ResponseEntity.ok(ventaActualizada);
        } catch (EstadoVentaInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Estado de venta inválido. " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada con ID: " + id);
        }
    }

    // Eliminar una venta por su ID con mensaje claro en caso de error
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVenta(@PathVariable Long id) {
        try {
            ventaService.eliminarVenta(id);
            return ResponseEntity.ok("Venta eliminada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar venta: Venta no encontrada con ID: " + id);
        }
    }
}
