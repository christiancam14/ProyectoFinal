package co.edu.uniquindio.ProyectoFinalp3.Entity;

import java.time.LocalDateTime;
import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import co.edu.uniquindio.ProyectoFinalp3.util.LocalDateTimeAdapter;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime fechaVenta;
    
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private VendedorEntity vendedor;

    @Enumerated(EnumType.STRING)
    private EstadoVentaEntity estadoVenta;
}
