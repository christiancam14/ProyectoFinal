package co.edu.uniquindio.ProyectoFinalp3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    Optional<Vendedor> findByCorreoElectronico(String username);

}
