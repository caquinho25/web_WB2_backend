package pe.edu.upc.wellnessbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wellnessbuddy.entities.InteraccionChatbot;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IInteraccionChatbotRepository extends JpaRepository<InteraccionChatbot, Integer> {

    // por usuario
    @Query("SELECT c FROM InteraccionChatbot c WHERE c.usuario.idUsuario = :idUsuario")
    List<InteraccionChatbot> buscarPorUsuario(@Param("idUsuario") int idUsuario);

    //por fecha
    @Query("SELECT c FROM InteraccionChatbot c WHERE c.fecha = :fecha")
    List<InteraccionChatbot> buscarPorFecha(@Param("fecha") LocalDate fecha);
}
