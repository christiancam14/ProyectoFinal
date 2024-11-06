package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorServiceImpl implements VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Override
    public Vendedor crearVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    @Override
    public Optional<Vendedor> obtenerVendedorPorId(Long id) {
        return vendedorRepository.findById(id);
    }

    @Override
    public Vendedor actualizarVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    @Override
    public void eliminarVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }

    @Override
    public List<Vendedor> listarVendedores() {
        return vendedorRepository.findAll();
    }

    @Override
    public List<Producto> obtenerProductosDeVendedor(Long vendedorId) {
        return obtenerVendedorPorId(vendedorId).map(Vendedor::getProductos).orElseThrow(() -> new IllegalArgumentException("Vendedor no encontrado"));
    }

    @Override
    public void agregarContactoAliado(Long vendedorId, Long aliadoId) {
        Vendedor vendedor = obtenerVendedorPorId(vendedorId).orElseThrow(() -> new IllegalArgumentException("Vendedor no encontrado"));
        Vendedor aliado = obtenerVendedorPorId(aliadoId).orElseThrow(() -> new IllegalArgumentException("Aliado no encontrado"));

        if (!vendedor.getContactosAliados().contains(aliado)) {
            vendedor.getContactosAliados().add(aliado);
            vendedorRepository.save(vendedor);
        }
    }

    @Override
    public void eliminarContactoAliado(Long vendedorId, Long aliadoId) {
        Vendedor vendedor = obtenerVendedorPorId(vendedorId).orElseThrow(() -> new IllegalArgumentException("Vendedor no encontrado"));
        Vendedor aliado = obtenerVendedorPorId(aliadoId).orElseThrow(() -> new IllegalArgumentException("Aliado no encontrado"));

        vendedor.getContactosAliados().remove(aliado);
        vendedorRepository.save(vendedor);
    }
}
