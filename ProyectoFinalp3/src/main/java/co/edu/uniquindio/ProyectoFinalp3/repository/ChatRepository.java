package co.edu.uniquindio.ProyectoFinalp3.repository;

import co.edu.uniquindio.ProyectoFinalp3.models.Chat;
import co.edu.uniquindio.ProyectoFinalp3.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    // Encuentra todos los chats en los que participa un usuario específico
    List<Chat> findByUser1OrUser2(User user1, User user2);

    List<Chat> findByUser1_IdOrUser2_Id(Long userId1, Long userId2);

}
