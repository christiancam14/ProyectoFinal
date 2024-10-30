package co.edu.uniquindio.ProyectoFinalp3.repository;

import co.edu.uniquindio.ProyectoFinalp3.models.Administrador;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    Optional<Administrador> findByEmail(String email);

}
