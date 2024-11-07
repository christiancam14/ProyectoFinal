package co.edu.uniquindio.ProyectoFinalp3.controllers;

import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.services.ProductoService;
import co.edu.uniquindio.ProyectoFinalp3.services.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticasController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private EstadisticasService estadisticasService;

    // Obtener Top 10 Productos con más "me gusta"
    @GetMapping("/top-productos-likes")
    public ResponseEntity<List<Producto>> obtenerTopProductosConMasLikes() {
        List<Producto> productos = productoService.obtenerTopProductosConMasLikes();
        return ResponseEntity.ok(productos);
    }

    // Obtener Top 10 Productos más vendidos
    @GetMapping("/top-productos-vendidos")
    public ResponseEntity<List<Producto>> obtenerTopProductosMasVendidos() {
        List<Producto> productos = productoService.obtenerTopProductosMasVendidos();
        return ResponseEntity.ok(productos);
    }

    // Exportar estadísticas a un archivo
    @GetMapping("/exportar")
    public ResponseEntity<?> exportarEstadisticas() {
        try {
            estadisticasService.exportarEstadisticas();
            return ResponseEntity.ok("Estadísticas exportadas exitosamente a 'estadisticas.txt'.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al exportar estadísticas: " + e.getMessage());
        }
    }
}

