package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EstadisticasServiceImpl implements EstadisticasService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void exportarEstadisticas() throws IOException {
        List<Producto> topProductosLikes = productoRepository.findTop10ByOrderByLikesDesc();
        List<Producto> topProductosVendidos = productoRepository.findTop10ByOrderByUnidadesVendidasDesc();

        // Crear el archivo para exportar las estadísticas
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("estadisticas.txt"))) {
            writer.write("Estadísticas del Sistema\n");
            writer.write("Fecha de exportación: " + LocalDate.now() + "\n\n");

            writer.write("Top 10 Productos con Más Me Gusta:\n");
            for (Producto producto : topProductosLikes) {
                writer.write("Producto: " + producto.getNombre() + " - Likes: " + producto.getLikes() + "\n");
            }

            writer.write("\nTop 10 Productos Más Vendidos:\n");
            for (Producto producto : topProductosVendidos) {
                writer.write("Producto: " + producto.getNombre() + " - Unidades Vendidas: " + producto.getUnidadesVendidas() + "\n");
            }
        }
    }
}
