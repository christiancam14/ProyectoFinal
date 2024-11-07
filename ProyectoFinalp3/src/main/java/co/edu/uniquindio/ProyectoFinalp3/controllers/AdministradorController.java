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

    // Obtener la instancia única del administrador con mensaje claro
    @GetMapping
    public ResponseEntity<?> obtenerAdministrador() {
        Administrador administrador = administradorService.obtenerAdministrador();
        if (administrador != null) {
            return ResponseEntity.ok(administrador);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado.");
        }
    }

    // Crear o actualizar la instancia única del administrador con mensaje claro
    @PostMapping
    public ResponseEntity<?> crearOActualizarAdministrador(@RequestBody Administrador administradorDetalles) {
        try {
            Administrador adminGuardado = administradorService.crearOActualizarAdministrador(administradorDetalles);
            return ResponseEntity.status(HttpStatus.CREATED).body("Administrador creado o actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear o actualizar el administrador: " + e.getMessage());
        }
    }

    // Actualizar la información del administrador con mensaje claro en caso de error
    @PutMapping
    public ResponseEntity<?> actualizarAdministrador(@RequestBody Administrador administradorDetalles) {
        Administrador adminActualizado = administradorService.actualizarAdministrador(administradorDetalles);
        if (adminActualizado != null) {
            return ResponseEntity.ok("Administrador actualizado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado para actualizar.");
        }
    }

    // Obtener estadísticas de los vendedores con respuesta clara
    @GetMapping("/estadisticas")
    public ResponseEntity<?> obtenerEstadisticas() {
        Administrador administrador = administradorService.obtenerAdministrador();
        if (administrador != null) {
            try {
                administrador.obtenerEstadisticas();
                return ResponseEntity.ok("Estadísticas obtenidas exitosamente.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener estadísticas: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado.");
        }
    }

    // Gestionar vendedores: activar, desactivar o eliminar con mensajes claros
    @PostMapping("/gestionar")
    public ResponseEntity<?> gestionarVendedores(@RequestParam String nombreVendedor, @RequestParam String accion) {
        Administrador administrador = administradorService.obtenerAdministrador();
        if (administrador != null) {
            try {
                administrador.gestionarVendedores(nombreVendedor, accion);
                return ResponseEntity.ok("Acción '" + accion + "' ejecutada sobre el vendedor '" + nombreVendedor + "'.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al ejecutar acción sobre el vendedor: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado.");
        }
    }
}
