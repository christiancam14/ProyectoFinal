package co.edu.uniquindio.ProyectoFinalp3.services;

import co.edu.uniquindio.ProyectoFinalp3.models.Administrador;
import co.edu.uniquindio.ProyectoFinalp3.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public Administrador crearAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public Optional<Administrador> obtenerAdministradorPorId(Long id) {
        return administradorRepository.findById(id);
    }

    @Override
    public List<Administrador> listarAdministradores() {
        return administradorRepository.findAll();
    }

    @Override
    public Administrador actualizarAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public void eliminarAdministrador(Long id) {
        administradorRepository.deleteById(id);
    }
}
