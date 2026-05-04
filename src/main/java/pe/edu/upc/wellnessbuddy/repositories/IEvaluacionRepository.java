package pe.edu.upc.wellnessbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wellnessbuddy.entities.Evaluacion;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IEvaluacionRepository extends JpaRepository<Evaluacion, Integer> {

    // por empleado
    @Query("SELECT e FROM Evaluacion e WHERE e.empleado.idEmpleado = :idEmpleado")
    List<Evaluacion> buscarPorEmpleado(@Param("idEmpleado") int idEmpleado);

    // por fecha
    @Query("SELECT e FROM Evaluacion e WHERE e.fecha = :fecha")
    List<Evaluacion> buscarPorFecha(@Param("fecha") LocalDate fecha);
}
