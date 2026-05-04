package pe.edu.upc.wellnessbuddy.servicesinterfaces;

import pe.edu.upc.wellnessbuddy.entities.Recomendacion;

import java.util.List;

public interface IRecomendacionService {
    public List<Recomendacion> list();
    public void insert(Recomendacion r);
    public Recomendacion listId(int id);
    public void update(Recomendacion r);
    public void delete(int id);
}
