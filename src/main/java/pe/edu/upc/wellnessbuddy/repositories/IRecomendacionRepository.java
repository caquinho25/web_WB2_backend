package pe.edu.upc.wellnessbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wellnessbuddy.entities.Recomendacion;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IRecomendacionRepository extends JpaRepository<Recomendacion, Integer> {

    @Query("SELECT r FROM Recomendacion r WHERE r.registro.idRegistro = :idRegistro")
    List<Recomendacion> buscarPorRegistro(@Param("idRegistro") int idRegistro);

    @Query("SELECT r FROM Recomendacion r WHERE r.fecha = :fecha")
    List<Recomendacion> buscarPorFecha(@Param("fecha") LocalDate fecha);

    @Query("SELECT r FROM Recomendacion r WHERE r.registro.empleado.idEmpleado = :idEmpleado")
    List<Recomendacion> buscarPorEmpleado(@Param("idEmpleado") int idEmpleado);
}
