package co.edu.uniquindio.ProyectoFinalp3.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Vendedor implements Serializable {

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
    private Muro muro;

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) // Relación uno a muchos con las ventas
    private List<Venta> ventas = new ArrayList<>();

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) // Relación uno a muchos con los productos
    private List<Producto> productos = new ArrayList<>();

    @ManyToMany // Relación muchos a muchos con otros vendedores como contactos aliados
    @JoinTable(
        name = "contactos_aliados",
        joinColumns = @JoinColumn(name = "vendedor_id"),
        inverseJoinColumns = @JoinColumn(name = "aliado_id")
    )
    private List<Vendedor> contactosAliados = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Vendedor() {}

    // Constructor con parámetros
    public Vendedor(String nombre, String telefono, String contrasena, String correoElectronico, String ciudad, String direccion, Muro muro) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.muro = muro;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Muro getMuro() {
        return muro;
    }

    public void setMuro(Muro muro) {
        this.muro = muro;
    }

    public List<Vendedor> getContactosAliados() {
        return contactosAliados;
    }

    public void setContactosAliados(List<Vendedor> contactosAliados) {
        this.contactosAliados = contactosAliados;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    // Método para agregar un producto al inventario del vendedor
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        System.out.println("Producto agregado: " + producto.getNombre());
    }

    // Método para eliminar un producto del inventario
    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        System.out.println("Producto eliminado: " + producto.getNombre());
    }

    // Método para registrar la venta de un producto
    public void registrarVenta(Producto producto, int cantidad) {
        // Verifica si el producto pertenece al vendedor
        if (productos.contains(producto)) {
            producto.registrarVenta(this, cantidad);  // Llama al método de registro de venta en la clase Producto
            
            // Crea la venta y la añade a la lista de ventas del vendedor
            Venta nuevaVenta = new Venta(producto, cantidad, this);
            ventas.add(nuevaVenta);
        } else {
            System.out.println("El producto no pertenece al inventario de este vendedor.");
        }
    }

    // Método para obtener el total de ventas
    public double obtenerTotalVentas() {
        return ventas.stream()
                .mapToDouble(Venta::getPrecioTotal)  // Sumamos el precio total de cada venta
                .sum();
    }

    // Método para obtener el producto más vendido
    public Producto obtenerProductoMasVendido() {
        return productos.stream()
                .max(Comparator.comparing(Producto::getUnidadesVendidas))  // Comparamos por las unidades vendidas
                .orElse(null);  // Devolvemos null si no hay productos
    }

    // Método para obtener el top 10 de productos más vendidos
    public List<Producto> obtenerTop10ProductosMasVendidos() {
        return productos.stream()
                .sorted(Comparator.comparing(Producto::getUnidadesVendidas).reversed())  // Ordenamos de mayor a menor
                .limit(10)  // Limitar a los 10 primeros
                .collect(Collectors.toList());
    }

    // Métodos para gestionar contactos aliados

    // Agregar un contacto aliado
    public void agregarContactoAliado(Vendedor aliado) {
        if (!contactosAliados.contains(aliado)) {
            contactosAliados.add(aliado);
            System.out.println("Contacto aliado agregado: " + aliado.getNombre());
        } else {
            System.out.println("El contacto ya es un aliado.");
        }
    }

    // Eliminar un contacto aliado
    public void deleteContactoAliado(Vendedor aliado) {
        if (contactosAliados.contains(aliado)) {
            contactosAliados.remove(aliado);
            System.out.println("Contacto aliado eliminado: " + aliado.getNombre());
        } else {
            System.out.println("El contacto no es un aliado.");
        }
    }
}