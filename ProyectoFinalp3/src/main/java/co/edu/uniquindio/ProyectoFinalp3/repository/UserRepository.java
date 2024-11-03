package co.edu.uniquindio.ProyectoFinalp3.repository;

import java.util.Optional; // Importa Optional
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniquindio.ProyectoFinalp3.models.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByEmail(String email);

    // Cambia el retorno a Optional<User>
    Optional<AppUser> findByEmail(String email);

}