package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.MarketPlace;
import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import co.edu.uniquindio.ProyectoFinalp3.exceptions.VendedorNoExistenteException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketPlaceServiceImpl implements MarketPlaceService {

    private MarketPlace marketPlace;

    public MarketPlaceServiceImpl() {
        // Inicializar la instancia de MarketPlace con un nombre predeterminado
        this.marketPlace = MarketPlace.getInstancia("MarketPlace Único");
    }

    @Override
    public MarketPlace obtenerInstanciaMarketPlace(String nombre) {
        return MarketPlace.getInstancia(nombre);
    }

    @Override
    public void registrarVendedor(String nombre, String contrasena, String ciudad, String telefono, String direccion, String correoElectronico) {
        marketPlace.registrarVendedor(nombre, contrasena, ciudad, telefono, direccion, correoElectronico);
    }

    @Override
    public void loginVendedor(String nombre, String contrasena) throws VendedorNoExistenteException {
        marketPlace.login(nombre, contrasena);
    }

    @Override
    public Optional<Vendedor> buscarVendedorPorNombre(String nombre) {
        return marketPlace.buscarVendedorPorNombre(nombre);
    }

    @Override
    public List<Vendedor> obtenerVendedores() {
        return marketPlace.getVendedores();
    }

    @Override
    public void sugerirVendedor() {
        marketPlace.sugerirVendedor();
    }
}
