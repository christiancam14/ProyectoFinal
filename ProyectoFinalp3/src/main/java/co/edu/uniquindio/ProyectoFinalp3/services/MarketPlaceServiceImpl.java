package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.MarketPlace;
import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import co.edu.uniquindio.ProyectoFinalp3.repository.MarketPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketPlaceServiceImpl implements MarketPlaceService {

    @Autowired
    private MarketPlaceRepository marketPlaceRepository;

    @Override
    public MarketPlace crearMarketPlace(MarketPlace marketPlace) {
        return marketPlaceRepository.save(marketPlace);
    }

    @Override
    public Optional<MarketPlace> obtenerMarketPlacePorId(Long id) {
        return marketPlaceRepository.findById(id);
    }

    @Override
    public void registrarVendedor(Long marketPlaceId, String nombre, String contrasena, String ciudad, String telefono, String direccion, String correoElectronico) {
        Optional<MarketPlace> marketPlaceOptional = marketPlaceRepository.findById(marketPlaceId);
        if (marketPlaceOptional.isPresent()) {
            MarketPlace marketPlace = marketPlaceOptional.get();
            marketPlace.registrar(nombre, contrasena, ciudad, telefono, direccion, correoElectronico);
            marketPlaceRepository.save(marketPlace); // Guardar el nuevo vendedor en el MarketPlace
        } else {
            System.out.println("MarketPlace no encontrado.");
        }
    }

    @Override
    public void loginVendedor(Long marketPlaceId, String nombre, String contrasena) {
        Optional<MarketPlace> marketPlaceOptional = marketPlaceRepository.findById(marketPlaceId);
        if (marketPlaceOptional.isPresent()) {
            MarketPlace marketPlace = marketPlaceOptional.get();
            marketPlace.login(nombre, contrasena);
        } else {
            System.out.println("MarketPlace no encontrado.");
        }
    }

    @Override
    public Optional<Vendedor> buscarVendedorPorNombre(Long marketPlaceId, String nombre) {
        Optional<MarketPlace> marketPlaceOptional = marketPlaceRepository.findById(marketPlaceId);
        if (marketPlaceOptional.isPresent()) {
            return marketPlaceOptional.get().buscarVendedorPorNombre(nombre);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void sugerirVendedor(Long marketPlaceId) {
        Optional<MarketPlace> marketPlaceOptional = marketPlaceRepository.findById(marketPlaceId);
        if (marketPlaceOptional.isPresent()) {
            MarketPlace marketPlace = marketPlaceOptional.get();
            marketPlace.sugerirVendedor();
        } else {
            System.out.println("MarketPlace no encontrado.");
        }
    }

    @Override
    public List<Vendedor> obtenerVendedores(Long marketPlaceId) {
        Optional<MarketPlace> marketPlaceOptional = marketPlaceRepository.findById(marketPlaceId);
        return marketPlaceOptional.map(MarketPlace::getVendedores).orElse(null);
    }
}

