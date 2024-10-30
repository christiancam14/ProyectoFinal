package co.edu.uniquindio.ProyectoFinalp3.Entity;

import java.util.ArrayList;

import java.util.List;

import co.edu.uniquindio.ProyectoFinalp3.models.Producto;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class VendedorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // El nombre es obligatorio y único
    private String nombre;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false, unique = true) // El correo electrónico debe ser único
    private String correoElectronico;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String direccion;

    @OneToOne(cascade = CascadeType.ALL) // Relación uno a uno con la entidad Muro
    @JoinColumn(name = "muro_id", referencedColumnName = "id")
    private MuroEntity muro;

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<VentaEntity> ventas = new ArrayList<>();

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Producto> productos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "contactos_aliados",
        joinColumns = @JoinColumn(name = "vendedor_id"),
        inverseJoinColumns = @JoinColumn(name = "aliado_id")
    )
    private List<VendedorEntity> contactosAliados = new ArrayList<>();

    // Relación con MarketPlace: cada Vendedor pertenece a un único MarketPlace
    @ManyToOne
    @JoinColumn(name = "marketplace_id")
    private MarketPlaceEntity marketPlace;

}
