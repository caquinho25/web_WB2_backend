package pe.edu.upc.wellnessbuddy.servicesinterfaces;

import pe.edu.upc.wellnessbuddy.entities.Comentario;

import java.util.List;

public interface IComentarioService {
    public List<Comentario> list();
    public void insert(Comentario c);
    public Comentario listId(int id);
    public void update(Comentario c);
    public void delete(int id);
}
