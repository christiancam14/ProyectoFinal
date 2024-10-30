package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Administrador;

public interface AdministradorService {

    Administrador obtenerAdministrador(); // Método para obtener el único administrador
    Administrador crearOActualizarAdministrador(Administrador administrador); // Método único para creación/actualización
    Administrador actualizarAdministrador(Administrador administrador);
}
