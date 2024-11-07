package co.edu.uniquindio.ProyectoFinalp3.controllers;

import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import co.edu.uniquindio.ProyectoFinalp3.services.MarketPlaceService;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.VendedorNoExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marketplace")
public class MarketPlaceController {

    @Autowired
    private MarketPlaceService marketPlaceService;

    // Endpoint para registrar un nuevo vendedor con mensajes claros
    @PostMapping("/vendedores")
    public ResponseEntity<?> registrarVendedor(
            @RequestParam String nombre,
            @RequestParam String contrasena,
            @RequestParam String ciudad,
            @RequestParam String telefono,
            @RequestParam String direccion,
            @RequestParam String correoElectronico) {

        try {
            marketPlaceService.registrarVendedor(nombre, contrasena, ciudad, telefono, direccion, correoElectronico);
            return ResponseEntity.status(HttpStatus.CREATED).body("Vendedor registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar vendedor: " + e.getMessage());
        }
    }

    // Endpoint para login de un vendedor con manejo de errores
    @PostMapping("/login")
    public ResponseEntity<?> loginVendedor(@RequestParam String nombre, @RequestParam String contrasena) {
        try {
            marketPlaceService.loginVendedor(nombre, contrasena);
            return ResponseEntity.ok("Login exitoso para el vendedor: " + nombre);
        } catch (VendedorNoExistenteException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado en el login: " + e.getMessage());
        }
    }
    // Endpoint para buscar un vendedor por nombre con mensajes claros
    @GetMapping("/vendedores/{nombre}")
    public ResponseEntity<?> buscarVendedorPorNombre(@PathVariable String nombre) {
    Optional<Vendedor> vendedor = marketPlaceService.buscarVendedorPorNombre(nombre);
    return vendedor
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vendedor con nombre '" + nombre + "' no encontrado."));
}
    // Endpoint para obtener todos los vendedores con mensaje claro
    @GetMapping("/vendedores")
    public ResponseEntity<?> obtenerVendedores() {
        List<Vendedor> vendedores = marketPlaceService.obtenerVendedores();
        if (vendedores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay vendedores registrados.");
        }
        return ResponseEntity.ok(vendedores);
    }

    // Endpoint para sugerir un vendedor con mensaje claro
    @GetMapping("/sugerencia")
    public ResponseEntity<String> sugerirVendedor() {
    String sugerencia = marketPlaceService.sugerirVendedor();
    return ResponseEntity.ok(sugerencia);
}

}