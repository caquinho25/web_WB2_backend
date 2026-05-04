package pe.edu.upc.wellnessbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wellnessbuddy.entities.Empleado;

import java.util.List;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer> {

    // empleados por empresa
    @Query("SELECT e FROM Empleado e WHERE e.empresa.idEmpresa = :idEmpresa")
    List<Empleado> buscarPorEmpresa(@Param("idEmpresa") int idEmpresa);

    // empleados activos
    @Query("SELECT e FROM Empleado e WHERE e.estado = true")
    List<Empleado> empleadosActivos();
}
