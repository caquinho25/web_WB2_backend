package pe.edu.upc.wellnessbuddy.servicesinterfaces;

import pe.edu.upc.wellnessbuddy.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> list();
    public void insert(Usuario u);
    public Usuario listId(int id);
    public void update(Usuario u);
    public void delete(int id);
    public void insertRole(String rol, int idUsuario);
    public Usuario buscarPorUsername(String username);
}
