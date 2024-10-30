package co.edu.uniquindio.ProyectoFinalp3.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import co.edu.uniquindio.ProyectoFinalp3.util.LocalDateTimeAdapter;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime fechaHoraPublicacion;
    @Enumerated(EnumType.STRING)
    private EstadoEntity estado;

    // Relación con Vendedor
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ComentarioEntity> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VentaEntity> ventas = new ArrayList<>();
}