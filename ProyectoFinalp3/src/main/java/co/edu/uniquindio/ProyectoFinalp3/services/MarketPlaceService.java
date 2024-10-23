package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.MarketPlace;
import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import java.util.List;
import java.util.Optional;

public interface MarketPlaceService {

    MarketPlace crearMarketPlace(MarketPlace marketPlace);

    Optional<MarketPlace> obtenerMarketPlacePorId(Long id);

    void registrarVendedor(Long marketPlaceId, String nombre, String contrasena, String ciudad, String telefono, String direccion, String correoElectronico);

    void loginVendedor(Long marketPlaceId, String nombre, String contrasena);

    Optional<Vendedor> buscarVendedorPorNombre(Long marketPlaceId, String nombre);

    void sugerirVendedor(Long marketPlaceId);

    List<Vendedor> obtenerVendedores(Long marketPlaceId);
}

