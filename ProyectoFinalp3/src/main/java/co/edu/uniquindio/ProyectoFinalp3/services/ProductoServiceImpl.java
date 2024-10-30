package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.models.Estado;
import co.edu.uniquindio.ProyectoFinalp3.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> obtenerProductosPorVendedor(Long vendedorId) {
        return productoRepository.findByVendedorId(vendedorId);
    }

    @Override
    public List<Producto> obtenerProductosPorEstado(Estado estado) {
        return productoRepository.findByEstado(estado);
    }
}
