package co.edu.uniquindio.ProyectoFinalp3.repository;

import co.edu.uniquindio.ProyectoFinalp3.models.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

    // Encuentra todos los comentarios de un producto específico
    List<ProductComment> findByProductId(Long productId);

    // Encuentra todos los comentarios de un usuario específico
    List<ProductComment> findByUserId(Long userId);
}