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

    // Crear una nueva venta
    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) {
        try {
            Venta nuevaVenta = ventaService.crearVenta(venta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
        } catch (ProductoSinUnidadesDisponiblesException | VentaNoValidaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Obtener una venta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable Long id) {
        Optional<Venta> ventaOpt = ventaService.obtenerVentaPorId(id);
        return ventaOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Listar todas las ventas
    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.listarVentas();
        return ResponseEntity.ok(ventas);
    }

    // Listar ventas por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Venta>> listarVentasPorEstado(@PathVariable EstadoVenta estado) {
        List<Venta> ventas = ventaService.listarVentasPorEstado(estado);
        return ResponseEntity.ok(ventas);
    }

    // Actualizar el estado de una venta
    @PutMapping("/{id}/estado")
    public ResponseEntity<Venta> actualizarEstadoVenta(@PathVariable Long id, @RequestParam EstadoVenta nuevoEstado) {
        try {
            Venta ventaActualizada = ventaService.actualizarEstadoVenta(id, nuevoEstado);
            return ResponseEntity.ok(ventaActualizada);
        } catch (EstadoVentaInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Eliminar una venta por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

