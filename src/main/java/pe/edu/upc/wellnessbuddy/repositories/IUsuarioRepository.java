package pe.edu.upc.wellnessbuddy.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.wellnessbuddy.entities.Usuario;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findOneByUsername(String username);

    @Query("SELECT u FROM Usuario u WHERE u.statusUsuario = true")
    List<Usuario> usuariosActivos();

    @Query("select count(u.username) from Usuario u where u.username =:username")
    public int buscarUsername(@Param("username") String nombre);

    @Transactional
    @Modifying
    @Query(value = "insert into roles (rol, id_usuario) VALUES (:rol, :id_usuario)", nativeQuery = true)
    public void insRol(@Param("rol") String authority,
                       @Param("id_usuario") int id_usuario);
}
