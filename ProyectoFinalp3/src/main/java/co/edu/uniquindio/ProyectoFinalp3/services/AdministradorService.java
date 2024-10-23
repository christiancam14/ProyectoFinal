package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Administrador;
import java.util.List;
import java.util.Optional;

public interface AdministradorService {

    Administrador crearAdministrador(Administrador administrador);
    Optional<Administrador> obtenerAdministradorPorId(Long id);
    List<Administrador> listarAdministradores();
    Administrador actualizarAdministrador(Administrador administrador);
    void eliminarAdministrador(Long id);
}

