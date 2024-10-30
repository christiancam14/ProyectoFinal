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

    @PostMapping
    public ResponseEntity<Vendedor> crearVendedor(@RequestBody Vendedor vendedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedorService.crearVendedor(vendedor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> obtenerVendedorPorId(@PathVariable Long id) {
        Optional<Vendedor> vendedor = vendedorService.obtenerVendedorPorId(id);
        return vendedor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> actualizarVendedor(@PathVariable Long id, @RequestBody Vendedor vendedorDetalles) {
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
            return ResponseEntity.ok(vendedorService.actualizarVendedor(vendedorActualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVendedor(@PathVariable Long id) {
        vendedorService.eliminarVendedor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Vendedor>> listarVendedores() {
        return ResponseEntity.ok(vendedorService.listarVendedores());
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<List<Producto>> obtenerProductosDeVendedor(@PathVariable Long id) {
        return ResponseEntity.ok(vendedorService.obtenerProductosDeVendedor(id));
    }

    @PostMapping("/{id}/contactos/{aliadoId}")
    public ResponseEntity<Void> agregarContactoAliado(@PathVariable Long id, @PathVariable Long aliadoId) {
        vendedorService.agregarContactoAliado(id, aliadoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/contactos/{aliadoId}")
    public ResponseEntity<Void> eliminarContactoAliado(@PathVariable Long id, @PathVariable Long aliadoId) {
        vendedorService.eliminarContactoAliado(id, aliadoId);
        return ResponseEntity.ok().build();
    }
}
