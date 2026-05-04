package pe.edu.upc.wellnessbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wellnessbuddy.entities.Comentario;

import java.util.List;

@Repository
public interface IComentarioRepository extends JpaRepository<Comentario, Integer> {

    // por empleado
    @Query("SELECT c FROM Comentario c WHERE c.empleado.idEmpleado = :idEmpleado")
    List<Comentario> buscarPorEmpleado(@Param("idEmpleado") int idEmpleado);

    //comentarios recientes
    @Query("SELECT c FROM Comentario c ORDER BY c.fecha DESC")
    List<Comentario> comentariosRecientes();
}
