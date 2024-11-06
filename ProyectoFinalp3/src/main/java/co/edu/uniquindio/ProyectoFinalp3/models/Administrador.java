package co.edu.uniquindio.ProyectoFinalp3.models;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import java.io.Serializable;

import co.edu.uniquindio.ProyectoFinalp3.exceptions.IdAdministradorInvalidoException;

@Entity
public class Administrador implements Serializable {

    public Long getId() {
        return id;
    }

    // Atributo estático de la instancia única
    private static Administrador instancia;

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

    // Constructor privado para el patrón Singleton
    private Administrador(String nombre, String telefono, String email, String contrasena, List<Vendedor> vendedores) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
        this.vendedores = vendedores;
    }

    public Administrador() {}

    // Método estático para obtener la única instancia de la clase
    public static Administrador getInstancia(String nombre, String telefono, String email, String contrasena, List<Vendedor> vendedores) {
        if (instancia == null) {
            instancia = new Administrador(nombre, telefono, email, contrasena, vendedores);
        }
        return instancia;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    // Método para obtener estadísticas de los vendedores
    public void obtenerEstadisticas() {
        System.out.println("Estadísticas generales de todos los vendedores:");
        vendedores.forEach(vendedor -> {
            System.out.println("Vendedor: " + vendedor.getNombre());
            vendedor.getVentas().forEach(venta -> {
                System.out.println(venta.generarEstadisticas());
            });
        });
    }

    public void verificarIdAdministrador(String id) throws IdAdministradorInvalidoException {
        if (id == null || id.isEmpty()) {
            throw new IdAdministradorInvalidoException("El ID del administrador no puede estar vacío o nulo.");
        }
    }

    // Método para gestionar a los vendedores (como activar, desactivar o eliminarlos)
    public void gestionarVendedores(String nombreVendedor, String accion) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNombre().equals(nombreVendedor)) {
                switch (accion.toLowerCase()) {
                    case "eliminar":
                        vendedores.remove(vendedor);
                        System.out.println("Vendedor " + nombreVendedor + " eliminado.");
                        break;
                    case "activar":
                        System.out.println("Vendedor " + nombreVendedor + " activado.");
                        break;
                    case "desactivar":
                        System.out.println("Vendedor " + nombreVendedor + " desactivado.");
                        break;
                    default:
                        System.out.println("Acción no válida.");
                        break;
                }
                break;
            }
        }
    }
}
