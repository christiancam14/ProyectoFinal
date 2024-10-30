package co.edu.uniquindio.ProyectoFinalp3.controllers;

import co.edu.uniquindio.ProyectoFinalp3.models.Administrador;
import co.edu.uniquindio.ProyectoFinalp3.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    // Obtener la instancia única del administrador
    @GetMapping
    public ResponseEntity<Administrador> obtenerAdministrador() {
        Administrador administrador = administradorService.obtenerAdministrador();
        if (administrador != null) {
            return ResponseEntity.ok(administrador);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Crear o actualizar la instancia única del administrador
    @PostMapping
    public ResponseEntity<Administrador> crearOActualizarAdministrador(@RequestBody Administrador administradorDetalles) {
        Administrador adminGuardado = administradorService.crearOActualizarAdministrador(administradorDetalles);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminGuardado);
    }

    // Actualizar la información del administrador
    @PutMapping
    public ResponseEntity<Administrador> actualizarAdministrador(@RequestBody Administrador administradorDetalles) {
        Administrador adminActualizado = administradorService.actualizarAdministrador(administradorDetalles);
        if (adminActualizado != null) {
            return ResponseEntity.ok(adminActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Obtener estadísticas de los vendedores del administrador
    @GetMapping("/estadisticas")
    public ResponseEntity<Void> obtenerEstadisticas() {
        Administrador administrador = administradorService.obtenerAdministrador();
        if (administrador != null) {
            administrador.obtenerEstadisticas();
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Gestionar vendedores: activar, desactivar o eliminar
    @PostMapping("/gestionar")
    public ResponseEntity<String> gestionarVendedores(@RequestParam String nombreVendedor, @RequestParam String accion) {
        Administrador administrador = administradorService.obtenerAdministrador();
        if (administrador != null) {
            administrador.gestionarVendedores(nombreVendedor, accion);
            return ResponseEntity.ok("Acción ejecutada sobre el vendedor.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado.");
        }
    }
}
