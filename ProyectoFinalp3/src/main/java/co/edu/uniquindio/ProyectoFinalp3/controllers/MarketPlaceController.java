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

    // Endpoint para registrar un nuevo vendedor
    @PostMapping("/vendedores")
    public ResponseEntity<String> registrarVendedor(
            @RequestParam String nombre,
            @RequestParam String contrasena,
            @RequestParam String ciudad,
            @RequestParam String telefono,
            @RequestParam String direccion,
            @RequestParam String correoElectronico) {

        marketPlaceService.registrarVendedor(nombre, contrasena, ciudad, telefono, direccion, correoElectronico);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vendedor registrado exitosamente");
    }

    // Endpoint para login de un vendedor
    @PostMapping("/login")
    public ResponseEntity<String> loginVendedor(@RequestParam String nombre, @RequestParam String contrasena) {
        try {
            marketPlaceService.loginVendedor(nombre, contrasena);
            return ResponseEntity.ok("Login exitoso para el vendedor: " + nombre);
        } catch (VendedorNoExistenteException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // Endpoint para buscar un vendedor por nombre
    @GetMapping("/vendedores/{nombre}")
    public ResponseEntity<Vendedor> buscarVendedorPorNombre(@PathVariable String nombre) {
        Optional<Vendedor> vendedor = marketPlaceService.buscarVendedorPorNombre(nombre);
        return vendedor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para obtener todos los vendedores
    @GetMapping("/vendedores")
    public ResponseEntity<List<Vendedor>> obtenerVendedores() {
        List<Vendedor> vendedores = marketPlaceService.obtenerVendedores();
        return ResponseEntity.ok(vendedores);
    }

    // Endpoint para sugerir un vendedor
    @GetMapping("/sugerencia")
    public ResponseEntity<String> sugerirVendedor() {
        marketPlaceService.sugerirVendedor();
        return ResponseEntity.ok("Sugerencia de vendedor mostrada en consola");
    }
}

