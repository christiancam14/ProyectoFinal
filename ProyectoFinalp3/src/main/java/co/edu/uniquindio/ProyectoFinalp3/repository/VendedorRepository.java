package co.edu.uniquindio.ProyectoFinalp3.repository;

import co.edu.uniquindio.ProyectoFinalp3.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    // Encuentra un vendedor por su correo electrónico
    Optional<Vendedor> findByCorreoElectronico(String correoElectronico);

    // Encuentra un vendedor por su nombre
    Optional<Vendedor> findByNombre(String nombre);
}

