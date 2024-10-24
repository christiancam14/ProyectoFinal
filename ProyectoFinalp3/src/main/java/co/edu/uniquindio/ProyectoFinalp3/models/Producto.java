package co.edu.uniquindio.ProyectoFinalp3.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHoraPublicacion;
    private String nombre;
    private String descripcion;
    private double precio;
    private int unidadesDisponibles;
    private int unidadesVendidas;
    private String imagen;
    private int likes;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    // Relación con Vendedor
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Producto() {}

    // Constructor
    public Producto(String nombre, String descripcion, double precio, int unidadesDisponibles, String imagen, Estado estado, Vendedor vendedor) {
        this.fechaHoraPublicacion = LocalDateTime.now();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidadesDisponibles = unidadesDisponibles;
        this.unidadesVendidas = 0;
        this.imagen = imagen;
        this.likes = 0;
        this.estado = estado;
        this.vendedor = vendedor;
        this.comentarios = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHoraPublicacion() {
        return fechaHoraPublicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(int unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    // Métodos
    public void incrementarLikes() {
        this.likes++;
    }

    public void agregarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }

    public boolean validarDisponibilidad(int cantidad) {
        return this.unidadesDisponibles >= cantidad;
    }

    public void registrarVenta(Vendedor vendedor, int cantidad) {
        if (validarDisponibilidad(cantidad)) {
            this.unidadesDisponibles -= cantidad;
            this.unidadesVendidas += cantidad;

            Venta nuevaVenta = new Venta(this, cantidad, vendedor);
            this.ventas.add(nuevaVenta);

            actualizarEstado();
        }
    }

    public void actualizarEstado() {
        if (this.unidadesDisponibles == 0) {
            this.estado = Estado.AGOTADO;
        }
    }
}
