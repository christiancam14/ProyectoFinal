package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.MarketPlace;
import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.VendedorNoExistenteException;

import java.util.List;
import java.util.Optional;

public interface MarketPlaceService {

    MarketPlace obtenerInstanciaMarketPlace(String nombre);

    void registrarVendedor(String nombre, String contrasena, String ciudad, String telefono, String direccion, String correoElectronico);

    void loginVendedor(String nombre, String contrasena) throws VendedorNoExistenteException;

    Optional<Vendedor> buscarVendedorPorNombre(String nombre);

    List<Vendedor> obtenerVendedores();

    void sugerirVendedor();
}
