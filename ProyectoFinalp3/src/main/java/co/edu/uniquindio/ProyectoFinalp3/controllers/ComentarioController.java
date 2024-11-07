package co.edu.uniquindio.ProyectoFinalp3.controllers;

import co.edu.uniquindio.ProyectoFinalp3.models.Comentario;
import co.edu.uniquindio.ProyectoFinalp3.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    // Crear un nuevo comentario con respuesta clara
    @PostMapping
    public ResponseEntity<?> crearComentario(@RequestBody Comentario comentario) {
        try {
            Comentario comentarioCreado = comentarioService.crearComentario(comentario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Comentario creado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el comentario: " + e.getMessage());
        }
    }
    // Obtener un comentario por ID con mensaje claro
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerComentarioPorId(@PathVariable Long id) {
    return comentarioService.obtenerComentarioPorId(id)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentario no encontrado."));
}





    // Listar todos los comentarios de un producto específico con respuesta clara
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<?> listarComentariosPorProducto(@PathVariable Long productoId) {
        try {
            List<Comentario> comentarios = comentarioService.listarComentariosPorProducto(productoId);
            if (comentarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron comentarios para este producto.");
            }
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al listar comentarios: " + e.getMessage());
        }
    }

    // Actualizar un comentario por ID con respuesta clara en caso de éxito o error
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarComentario(@PathVariable Long id, @RequestBody Comentario comentarioDetalles) {
        return comentarioService.obtenerComentarioPorId(id)
                .map(comentarioExistente -> {
                    comentarioExistente.setContenido(comentarioDetalles.getContenido());
                    comentarioExistente.setProducto(comentarioDetalles.getProducto());
                    Comentario comentarioActualizado = comentarioService.actualizarComentario(comentarioExistente);
                    return ResponseEntity.ok("Comentario actualizado exitosamente.");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentario no encontrado para actualizar."));
    }

    // Eliminar un comentario por ID con mensaje claro en caso de éxito o error
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarComentario(@PathVariable Long id) {
        try {
            comentarioService.eliminarComentario(id);
            return ResponseEntity.ok("Comentario eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar comentario: " + e.getMessage());
        }
    }
}
