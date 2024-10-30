package co.edu.uniquindio.ProyectoFinalp3.controllers;

import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.models.Estado;
import co.edu.uniquindio.ProyectoFinalp3.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto productoCreado = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);
        return producto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        Optional<Producto> productoExistente = productoService.obtenerProductoPorId(id);
        if (productoExistente.isPresent()) {
            Producto productoActualizado = productoExistente.get();
            productoActualizado.setNombre(productoDetalles.getNombre());
            productoActualizado.setDescripcion(productoDetalles.getDescripcion());
            productoActualizado.setPrecio(productoDetalles.getPrecio());
            productoActualizado.setUnidadesDisponibles(productoDetalles.getUnidadesDisponibles());
            productoActualizado.setImagen(productoDetalles.getImagen());
            productoActualizado.setEstado(productoDetalles.getEstado());
            return ResponseEntity.ok(productoService.actualizarProducto(productoActualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vendedor/{vendedorId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorVendedor(@PathVariable Long vendedorId) {
        return ResponseEntity.ok(productoService.obtenerProductosPorVendedor(vendedorId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Producto>> obtenerProductosPorEstado(@PathVariable Estado estado) {
        return ResponseEntity.ok(productoService.obtenerProductosPorEstado(estado));
    }
}
