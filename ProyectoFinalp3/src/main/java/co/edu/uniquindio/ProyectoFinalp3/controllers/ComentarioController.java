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

    @PostMapping
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario) {
        Comentario comentarioCreado = comentarioService.crearComentario(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioCreado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable Long id) {
        return comentarioService.obtenerComentarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Comentario>> listarComentariosPorProducto(@PathVariable Long productoId) {
        List<Comentario> comentarios = comentarioService.listarComentariosPorProducto(productoId);
        return ResponseEntity.ok(comentarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(
            @PathVariable Long id, @RequestBody Comentario comentarioDetalles) {

        return comentarioService.obtenerComentarioPorId(id)
                .map(comentarioExistente -> {
                    comentarioExistente.setContenido(comentarioDetalles.getContenido());
                    comentarioExistente.setProducto(comentarioDetalles.getProducto());
                    Comentario comentarioActualizado = comentarioService.actualizarComentario(comentarioExistente);
                    return ResponseEntity.ok(comentarioActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }
}

