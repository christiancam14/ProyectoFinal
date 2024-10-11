package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import co.edu.uniquindio.ProyectoFinalp3.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    // Obtener un vendedor por ID
    public Optional<Vendedor> obtenerVendedorPorId(Long id) {
        return vendedorRepository.findById(id);
    }
    //Crear un vendedor 
    public Vendedor crearVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    // Publicar un producto en el muro del vendedor
    public String publicarProducto(Long idVendedor, Producto producto) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(idVendedor);
        if (vendedorOptional.isPresent()) {
            Vendedor vendedor = vendedorOptional.get();
            vendedor.agregarProducto(producto);
            vendedorRepository.save(vendedor);  // Guardar los cambios en la base de datos
            return "Producto publicado exitosamente";
        } else {
            return "Vendedor no encontrado";
        }
    }

    // Obtener los productos de un vendedor
    public List<Producto> obtenerProductosDeVendedor(Long idVendedor) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(idVendedor);
        return vendedorOptional.map(Vendedor::getProductos).orElse(null);
    }

    // Agregar un contacto aliado
    public String agregarContactoAliado(Long idVendedor, Vendedor aliado) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(idVendedor);
        if (vendedorOptional.isPresent()) {
            Vendedor vendedor = vendedorOptional.get();
            vendedor.agregarContactoAliado(aliado);
            vendedorRepository.save(vendedor);  // Guardar los cambios en la base de datos
            return "Contacto aliado agregado exitosamente";
        } else {
            return "Vendedor no encontrado";
        }
    }

    // Eliminar un contacto aliado
    public String eliminarContactoAliado(Long idVendedor, Vendedor aliado) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(idVendedor);
        if (vendedorOptional.isPresent()) {
            Vendedor vendedor = vendedorOptional.get();
            vendedor.deleteContactoAliado(aliado);
            vendedorRepository.save(vendedor);  // Guardar los cambios en la base de datos
            return "Contacto aliado eliminado exitosamente";
        } else {
            return "Vendedor no encontrado";
        }
    }

    // Obtener el top 10 productos más vendidos
    public List<Producto> obtenerTop10ProductosMasVendidos(Long idVendedor) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(idVendedor);
        return vendedorOptional.map(Vendedor::obtenerTop10ProductosMasVendidos).orElse(null);
    }
}


