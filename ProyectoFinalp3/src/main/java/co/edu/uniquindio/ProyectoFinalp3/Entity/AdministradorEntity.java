package co.edu.uniquindio.ProyectoFinalp3.Entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import java.io.Serializable;

import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;

@Entity
public class AdministradorEntity implements Serializable {

    public Long getId() {
        return id;
    }

    // Atributo estático de la instancia única
    private static AdministradorEntity instancia;

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
    private String email;

    @ManyToMany
    @JoinTable(name = "entidad_vendedor", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "entidad_id"), // Clave de la tabla actual
            inverseJoinColumns = @JoinColumn(name = "vendedor_id") // Clave de la tabla Vendedor
    )
    private List<Vendedor> vendedores = new ArrayList<>();



}
