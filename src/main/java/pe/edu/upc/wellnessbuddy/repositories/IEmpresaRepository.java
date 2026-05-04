package pe.edu.upc.wellnessbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wellnessbuddy.entities.Empresa;

import java.util.List;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {

    //buscar por nombre
    @Query("SELECT e FROM Empresa e WHERE e.nombre LIKE %:nombre%")
    List<Empresa> buscarPorNombre(@Param("nombre") String nombre);

    //empresas activas
    @Query("SELECT e FROM Empresa e WHERE e.estado = true")
    List<Empresa> empresasActivas();
}
