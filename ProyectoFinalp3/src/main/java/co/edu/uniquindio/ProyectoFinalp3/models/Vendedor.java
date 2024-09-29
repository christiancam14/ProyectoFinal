package co.edu.uniquindio.ProyectoFinalp3.models;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Vendedor {

    // Atributos
    private String nombre;
    private String telefono;
    private String contrasena;
    private String correoElectronico;
    private String ciudad;
    private String direccion;
    private Muro muro;
    private List<Venta> ventas;  // Lista de     ventas realizadas por el vendedor
    private List<Producto> productos;  

    // Constructor
    public Vendedor(String nombre, String telefono, String contrasena, String correoElectronico, String ciudad, String direccion, Muro muro) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.muro = muro;
        this.ventas = new ArrayList<>();
        this.productos = new ArrayList<>();
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
    public void registrarVenta(Producto producto, int cantidad) throws ProductoYaVendidoException {
        // Verifica si el producto pertenece al vendedor
        if (productos.contains(producto)) {
            
            // Verifica si el producto ya ha sido vendido
            if (producto.getEstado() == Estado.VENDIDO) {
                throw new ProductoYaVendidoException("El producto " + producto.getNombre() + " ya ha sido vendido.");
            }
    
            // Verifica si el producto tiene suficiente stock
            if (producto.getUnidadesDisponibles() < cantidad);
            // Registrar la venta
            producto.registrarVenta(this, cantidad); // Llama al método de registro de venta en la clase Producto
            
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

    public List<Producto> obtenerTop10ProductosMasLikeados() throws MinimoProductosRequeridosException {
        if (productos.size() < 10) {
            throw new MinimoProductosRequeridosException("Se necesitan al menos 10 productos para calcular el top 10.");
        }
        return productos.stream()
            .sorted(Comparator.comparingInt(Producto::getLikes).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }
     
}
