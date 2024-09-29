package co.edu.uniquindio.ProyectoFinalp3.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Producto {

    // Atributos
    private LocalDateTime fechaHoraPublicacion;
    private String nombre;
    private String descripcion;
    private double precio;
    private int unidadesDisponibles;
    private int unidadesVendidas;
    private String imagen;
    private int likes;
    private Estado estado;
    private List<Comentario> comentarios;
    private List<Venta> ventas;  // Lista de ventas realizadas con este producto

    // Constructor
    public Producto(String nombre, String descripcion, double precio, int unidadesDisponibles, String imagen, Estado estado) {
        this.fechaHoraPublicacion = LocalDateTime.now();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidadesDisponibles = unidadesDisponibles;
        this.unidadesVendidas = 0; // Inicialmente, no se han vendido unidades
        this.imagen = imagen;
        this.likes = 0;  // Inicialmente sin likes
        this.estado = estado;
        this.comentarios = new ArrayList<>();
        this.ventas = new ArrayList<>();  // Inicialmente no hay ventas
    }

    // Getters y setters
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

    // Método para incrementar el número de likes
    public void incrementarLikes() {
        this.likes++;
    }

    // Método para agregar un comentario al producto
    public void agregarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }

    // Método para registrar una venta
    public void registrarVenta(Vendedor vendedor, int cantidad) {
        if (validarDisponibilidad(cantidad)) {
            this.unidadesDisponibles -= cantidad;
            this.unidadesVendidas += cantidad;
            
            // Crear una nueva venta y agregarla a la lista de ventas del producto
            Venta nuevaVenta = new Venta(this, cantidad, vendedor);
            this.ventas.add(nuevaVenta);
            
            // Actualizar el estado si las unidades disponibles llegan a cero
            actualizarEstado();
            
            System.out.println("Venta registrada exitosamente: " + cantidad + " unidades de " + nombre);
        } else {
            System.out.println("No hay suficientes unidades disponibles para realizar la venta.");
        }
    }

    // Método para validar la disponibilidad de stock
    public boolean validarDisponibilidad(int cantidad) {
        return this.unidadesDisponibles >= cantidad;
    }

    // Método para actualizar el estado del producto (por ejemplo, a 'AGOTADO' si no hay más unidades)
    public void actualizarEstado() {
        if (this.unidadesDisponibles == 0) {
            this.estado = Estado.AGOTADO;
        }
    }

}