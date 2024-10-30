package co.edu.uniquindio.ProyectoFinalp3.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.*;
import java.io.Serializable;

import co.edu.uniquindio.ProyectoFinalp3.exceptions.VendedorNoExistenteException;
import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;

@Entity
public class MarketPlaceEntity implements Serializable {

    // Atributo estático para implementar Singleton, se usa este patrón porque es un único market place.
    private static MarketPlaceEntity instancia;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "marketPlace")
    private List<Vendedor> vendedores = new ArrayList<>();

}