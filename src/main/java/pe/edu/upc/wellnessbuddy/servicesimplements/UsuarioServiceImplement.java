package pe.edu.upc.wellnessbuddy.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.wellnessbuddy.entities.Usuario;
import pe.edu.upc.wellnessbuddy.repositories.IUsuarioRepository;
import pe.edu.upc.wellnessbuddy.servicesinterfaces.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

    @Autowired
    private IUsuarioRepository uR;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> list() {
        return uR.findAll();
    }

    @Override
    public void insert(Usuario u) {
        String passwordBcrypt =
                passwordEncoder.encode(u.getPassword());
        u.setPassword(passwordBcrypt);
        uR.save(u);
        uR.insRol("USER", u.getIdUsuario());
    }

    @Override
    public Usuario listId(int id) {
        return uR.findById(id).orElse(null);
    }

    @Override
    public void update(Usuario u) {
        uR.save(u);
    }

    @Override
    public void delete(int id) {
        uR.deleteById(id);
    }

    @Override
    public void insertRole(String rol, int idUsuario) {
        uR.insRol(rol, idUsuario);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return uR.findOneByUsername(username);
    }

}
