package co.edu.uniquindio.ProyectoFinalp3.repository;

import co.edu.uniquindio.ProyectoFinalp3.models.Muro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuroRepository extends JpaRepository<Muro, Long> {
    
}

