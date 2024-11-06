package co.edu.uniquindio.ProyectoFinalp3.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.*;
import java.io.Serializable;

import co.edu.uniquindio.ProyectoFinalp3.exceptions.VendedorNoExistenteException;

@Entity
public class MarketPlace implements Serializable {

    // Atributo estático para implementar Singleton, se usa este patrón porque es un único market place.
    private static MarketPlace instancia;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "marketPlace")
    private List<Vendedor> vendedores = new ArrayList<>();

    // Constructor privado para patrón Singleton
    private MarketPlace(String nombre) {
        this.nombre = nombre;
    }

    // Método estático para obtener la instancia única del MarketPlace
    public static MarketPlace getInstancia(String nombre) {
        if (instancia == null) {
            instancia = new MarketPlace(nombre);
        }
        return instancia;
    }

    // Método para registrar un nuevo vendedor en el MarketPlace
    public void registrarVendedor(String nombre, String contrasena, String ciudad, String telefono, String direccion, String correoElectronico) {
        Vendedor nuevoVendedor = new Vendedor(nombre, telefono, contrasena, correoElectronico, ciudad, direccion, null);
        vendedores.add(nuevoVendedor);
        System.out.println("Vendedor registrado exitosamente: " + nombre);
    }

    // Método para iniciar sesión con un vendedor registrado
    public void login(String nombre, String contrasena) throws VendedorNoExistenteException {
        Optional<Vendedor> vendedor = buscarVendedorPorNombre(nombre);
        if (vendedor.isPresent() && vendedor.get().getContrasena().equals(contrasena)) {
            System.out.println("Login exitoso para el vendedor: " + nombre);
        } else {
            throw new VendedorNoExistenteException("Credenciales incorrectas o vendedor no existe.");
        }
    }

    // Método para buscar un vendedor por su nombre
    public Optional<Vendedor> buscarVendedorPorNombre(String nombre) {
        return vendedores.stream()
                .filter(v -> v.getNombre().equals(nombre))
                .findAny();
    }

    // Método para sugerir un vendedor 
    public void sugerirVendedor() {
        if (!vendedores.isEmpty()) {
            Vendedor vendedorSugerido = vendedores.get(0);
            System.out.println("Sugerencia de vendedor: " + vendedorSugerido.getNombre());
        } else {
            System.out.println("No hay vendedores registrados para sugerir.");
        }
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }
}