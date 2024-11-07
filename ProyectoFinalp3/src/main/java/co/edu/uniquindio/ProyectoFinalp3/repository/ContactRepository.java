package co.edu.uniquindio.ProyectoFinalp3.repository;

import co.edu.uniquindio.ProyectoFinalp3.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Encuentra todos los contactos de un usuario
    List<Contact> findByUserId(Long userId);

    // Encuentra el estado de un contacto específico
    Optional<Contact> findByUserIdAndContactUserId(Long userId, Long contactUserId);
}
