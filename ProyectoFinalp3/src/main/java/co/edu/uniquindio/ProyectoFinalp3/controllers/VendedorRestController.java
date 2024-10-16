package co.edu.uniquindio.ProyectoFinalp3.controllers;

import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import co.edu.uniquindio.ProyectoFinalp3.services.VendedorServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedor")
public class VendedorRestController {

    @Autowired
    private VendedorServices vendedorService;

    // Publicar un producto
    @PostMapping("/{id}/producto")
    public ResponseEntity<String> publicarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        String respuesta = vendedorService.publicarProducto(id, producto);
        return ResponseEntity.ok(respuesta);
    }

    // Obtener los productos de un vendedor
    @GetMapping("/{id}/productos")
    public ResponseEntity<List<Producto>> obtenerProductos(@PathVariable Long id) {
        List<Producto> productos = vendedorService.obtenerProductosDeVendedor(id);
        return ResponseEntity.ok(productos);
    }

    // Agregar un contacto aliado
    @PostMapping("/{id}/contacto")
    public ResponseEntity<String> agregarContactoAliado(@PathVariable Long id, @RequestBody Vendedor aliado) {
        String respuesta = vendedorService.agregarContactoAliado(id, aliado);
        return ResponseEntity.ok(respuesta);
    }

    // Eliminar un contacto aliado
    @DeleteMapping("/{id}/contacto")
    public ResponseEntity<String> eliminarContactoAliado(@PathVariable Long id, @RequestBody Vendedor aliado) {
        String respuesta = vendedorService.eliminarContactoAliado(id, aliado);
        return ResponseEntity.ok(respuesta);
    }

    // Obtener el top 10 productos más vendidos
    @GetMapping("/{id}/top10productos")
    public ResponseEntity<List<Producto>> obtenerTop10Productos(@PathVariable Long id) {
        List<Producto> top10 = vendedorService.obtenerTop10ProductosMasVendidos(id);
        return ResponseEntity.ok(top10);
    }
    @PostMapping("/crear")
    public ResponseEntity<Vendedor> crearVendedor(@RequestBody Vendedor vendedor) {
        Vendedor nuevoVendedor = vendedorService.crearVendedor(vendedor);
         return ResponseEntity.ok(nuevoVendedor);
}
}



