package co.edu.uniquindio.ProyectoFinalp3.controllers;

import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    // Crear un nuevo vendedor con respuesta clara
    @PostMapping
    public ResponseEntity<?> crearVendedor(@RequestBody Vendedor vendedor) {
        try {
            Vendedor nuevoVendedor = vendedorService.crearVendedor(vendedor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Vendedor creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el vendedor: " + e.getMessage());
        }
    }

    // Obtener un vendedor por ID con manejo de respuesta en caso de no encontrarlo
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVendedorPorId(@PathVariable Long id) {
        Optional<Vendedor> vendedor = vendedorService.obtenerVendedorPorId(id);
        if (vendedor.isPresent()) {
            return ResponseEntity.ok(vendedor.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor no encontrado");
        }
    }
    
    // Actualizar un vendedor por ID con respuesta clara en caso de éxito o error
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVendedor(@PathVariable Long id, @RequestBody Vendedor vendedorDetalles) {
        Optional<Vendedor> vendedorExistente = vendedorService.obtenerVendedorPorId(id);
        if (vendedorExistente.isPresent()) {
            Vendedor vendedorActualizado = vendedorExistente.get();
            vendedorActualizado.setNombre(vendedorDetalles.getNombre());
            vendedorActualizado.setTelefono(vendedorDetalles.getTelefono());
            vendedorActualizado.setContrasena(vendedorDetalles.getContrasena());
            vendedorActualizado.setCorreoElectronico(vendedorDetalles.getCorreoElectronico());
            vendedorActualizado.setCiudad(vendedorDetalles.getCiudad());
            vendedorActualizado.setDireccion(vendedorDetalles.getDireccion());
            vendedorActualizado.setMuro(vendedorDetalles.getMuro());
            vendedorService.actualizarVendedor(vendedorActualizado);
            return ResponseEntity.ok("Vendedor actualizado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor no encontrado para actualizar");
    }

    // Eliminar un vendedor por ID con mensaje en caso de no encontrarlo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVendedor(@PathVariable Long id) {
        try {
            vendedorService.eliminarVendedor(id);
            return ResponseEntity.ok("Vendedor eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar: Vendedor no encontrado");
        }
    }

    // Listar todos los vendedores con respuesta exitosa
    @GetMapping
    public ResponseEntity<List<Vendedor>> listarVendedores() {
        return ResponseEntity.ok(vendedorService.listarVendedores());
    }

    // Obtener productos de un vendedor específico
    @GetMapping("/{id}/productos")
    public ResponseEntity<?> obtenerProductosDeVendedor(@PathVariable Long id) {
        try {
            List<Producto> productos = vendedorService.obtenerProductosDeVendedor(id);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener productos: Vendedor no encontrado");
        }
    }

    // Agregar contacto aliado con mensajes claros
    @PostMapping("/{id}/contactos/{aliadoId}")
    public ResponseEntity<?> agregarContactoAliado(@PathVariable Long id, @PathVariable Long aliadoId) {
        try {
            vendedorService.agregarContactoAliado(id, aliadoId);
            return ResponseEntity.ok("Contacto aliado agregado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar contacto aliado: " + e.getMessage());
        }
    }

    // Eliminar contacto aliado con mensajes claros
    @DeleteMapping("/{id}/contactos/{aliadoId}")
    public ResponseEntity<?> eliminarContactoAliado(@PathVariable Long id, @PathVariable Long aliadoId) {
        try {
            vendedorService.eliminarContactoAliado(id, aliadoId);
            return ResponseEntity.ok("Contacto aliado eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar contacto aliado: " + e.getMessage());
        }
    }
}
