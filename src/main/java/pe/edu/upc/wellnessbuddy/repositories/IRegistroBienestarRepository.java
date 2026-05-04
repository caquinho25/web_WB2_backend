package pe.edu.upc.wellnessbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wellnessbuddy.entities.RegistroBienestar;

import java.util.List;

@Repository
public interface IRegistroBienestarRepository extends JpaRepository<RegistroBienestar, Integer> {

    // bienestar por empleado
    @Query("SELECT b FROM RegistroBienestar b WHERE b.empleado.idEmpleado = :idEmpleado")
    List<RegistroBienestar> buscarPorEmpleado(@Param("idEmpleado") int idEmpleado);

    // alto estres
    @Query("SELECT b FROM RegistroBienestar b WHERE b.nivelEstres > 7")
    List<RegistroBienestar> estresAlto();
}
