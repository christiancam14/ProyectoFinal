package co.edu.uniquindio.ProyectoFinalp3.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.io.Serializable;

import co.edu.uniquindio.ProyectoFinalp3.exceptions.EstadoVentaInvalidoException;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.ProductoSinUnidadesDisponiblesException;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.VentaNoValidaException;

@Entity
public class Venta implements Serializable {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Relación muchos a uno con Producto
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private LocalDateTime fechaVenta;

    @Column(nullable = false)
    private double precioTotal;

    @ManyToOne // Relación muchos a uno con Vendedor
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVenta estadoVenta;

    // Constructor
    public Venta(Producto producto, int cantidad, Vendedor vendedor) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.vendedor = vendedor;
        this.fechaVenta = LocalDateTime.now(); // Fecha de la venta
        this.precioTotal = calcularPrecioTotal();
        this.estadoVenta = EstadoVenta.PENDIENTE;
    }

    // Método para calcular el precio total de la venta
    private double calcularPrecioTotal() {
        return producto.getPrecio() * cantidad;
    }

    public boolean validarDisponibilidad() {
        return producto.getUnidadesDisponibles() >= cantidad;
    }

    // Getters y setters
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        this.precioTotal = calcularPrecioTotal();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.precioTotal = calcularPrecioTotal();
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public EstadoVenta getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVenta estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public void venderProducto(Producto producto, int cantidad) throws ProductoSinUnidadesDisponiblesException {
        if (producto.getUnidadesDisponibles() < cantidad) {
            throw new ProductoSinUnidadesDisponiblesException(
                    "No hay suficientes unidades disponibles para el producto: " + producto.getNombre());
        }
    }

    public void registrarVenta(Producto producto, int cantidad) throws VentaNoValidaException {
        if (producto.getUnidadesDisponibles() < cantidad) {
            throw new VentaNoValidaException("Venta no válida: stock insuficiente.");
        }
    }

    public void cambiarEstadoVenta(EstadoVenta nuevoEstado) throws EstadoVentaInvalidoException {
        if (nuevoEstado == null) {
            throw new EstadoVentaInvalidoException("El estado de venta es inválido.");
        }
        this.estadoVenta = nuevoEstado;
    }

    // Método para generar estadísticas de la venta
    public String generarEstadisticas() {
        StringBuilder estadisticas = new StringBuilder();
        estadisticas.append("Estadísticas de la Venta:\n");
        estadisticas.append("Producto: ").append(producto.getNombre()).append("\n");
        estadisticas.append("Cantidad Vendida: ").append(cantidad).append("\n");
        estadisticas.append("Precio Total de la Venta: ").append(precioTotal).append("\n");
        estadisticas.append("Fecha de la Venta: ").append(fechaVenta).append("\n");
        estadisticas.append("Vendedor: ").append(vendedor.getNombre()).append("\n");
        estadisticas.append("Estado de la Venta: ").append(estadoVenta).append("\n");

        return estadisticas.toString();
    }
}
