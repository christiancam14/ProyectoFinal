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

    // Crear un nuevo producto con respuesta clara
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        try {
            Producto productoCreado = productoService.crearProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el producto: " + e.getMessage());
        }
    }

    // Obtener un producto por ID con mensaje claro
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
    Optional<Producto> producto = productoService.obtenerProductoPorId(id);
    return producto
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto no encontrado con ID: " + id));
}


    // Listar todos los productos con respuesta exitosa
    @GetMapping
    public ResponseEntity<?> listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay productos disponibles.");
        }
        return ResponseEntity.ok(productos);
    }

    // Actualizar un producto por ID con mensaje claro en caso de éxito o error
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        Optional<Producto> productoExistente = productoService.obtenerProductoPorId(id);
        if (productoExistente.isPresent()) {
            Producto productoActualizado = productoExistente.get();
            productoActualizado.setNombre(productoDetalles.getNombre());
            productoActualizado.setDescripcion(productoDetalles.getDescripcion());
            productoActualizado.setPrecio(productoDetalles.getPrecio());
            productoActualizado.setUnidadesDisponibles(productoDetalles.getUnidadesDisponibles());
            productoActualizado.setImagen(productoDetalles.getImagen());
            productoActualizado.setEstado(productoDetalles.getEstado());
            productoService.actualizarProducto(productoActualizado);
            return ResponseEntity.ok("Producto actualizado exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + id);
    }

    // Eliminar un producto por ID con mensaje claro
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.ok("Producto eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar el producto: " + e.getMessage());
        }
    }

    // Obtener todos los productos de un vendedor específico con respuesta clara
    @GetMapping("/vendedor/{vendedorId}")
    public ResponseEntity<?> obtenerProductosPorVendedor(@PathVariable Long vendedorId) {
        List<Producto> productos = productoService.obtenerProductosPorVendedor(vendedorId);
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos para el vendedor con ID: " + vendedorId);
        }
        return ResponseEntity.ok(productos);
    }

    // Obtener productos por estado con mensaje claro
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> obtenerProductosPorEstado(@PathVariable Estado estado) {
        List<Producto> productos = productoService.obtenerProductosPorEstado(estado);
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos con el estado: " + estado);
        }
        return ResponseEntity.ok(productos);
    }
    
    @PutMapping("/{id}/like")
public ResponseEntity<?> darLike(@PathVariable Long id) {
    Optional<Producto> productoOpt = productoService.obtenerProductoPorId(id);
    if (productoOpt.isPresent()) {
        Producto producto = productoOpt.get();
        producto.incrementarLikes();
        productoService.actualizarProducto(producto);
        return ResponseEntity.ok("Producto con ID " + id + " recibió un me gusta. Total de likes: " + producto.getLikes());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + id);
    }
}

}
