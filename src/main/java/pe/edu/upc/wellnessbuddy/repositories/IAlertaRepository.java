package pe.edu.upc.wellnessbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wellnessbuddy.entities.Alerta;

import java.util.List;

@Repository
public interface IAlertaRepository extends JpaRepository<Alerta, Integer> {

    @Query("SELECT a FROM Alerta a WHERE a.tipo = :tipo")
    List<Alerta> buscarPorTipo(@Param("tipo") String tipo);

    @Query("SELECT a FROM Alerta a WHERE a.registro.idRegistro = :idRegistro")
    List<Alerta> buscarPorRegistro(@Param("idRegistro") int idRegistro);

    @Query("SELECT a FROM Alerta a WHERE a.registro.empleado.idEmpleado = :idEmpleado")
    List<Alerta> buscarPorEmpleado(@Param("idEmpleado") int idEmpleado);
}
