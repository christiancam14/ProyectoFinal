package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Administrador;
import co.edu.uniquindio.ProyectoFinalp3.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public Administrador obtenerAdministrador() {
        // Retorna la única instancia de administrador si existe
        return administradorRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("No existe ningún administrador en el sistema."));
    }

    @Override
    public Administrador crearOActualizarAdministrador(Administrador administrador) {
        Optional<Administrador> adminExistente = administradorRepository.findAll().stream().findFirst();
        if (adminExistente.isPresent()) {
            administrador.setId(adminExistente.get().getId()); // Mantiene el mismo ID
        }
        return administradorRepository.save(administrador);
    }

    @Override
    public Administrador actualizarAdministrador(Administrador administrador) {
        Administrador adminExistente = obtenerAdministrador();
        administrador.setId(adminExistente.getId()); // Asegura que el ID sea el mismo
        return administradorRepository.save(administrador);
    }
}
