package pe.edu.upc.wellnessbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wellnessbuddy.entities.Usuario;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    // buscar por username
    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Usuario buscarPorUsername(@Param("username") String username);

    // usuarios activos
    @Query("SELECT u FROM Usuario u WHERE u.statusUsuario = true")
    List<Usuario> usuariosActivos();
}
