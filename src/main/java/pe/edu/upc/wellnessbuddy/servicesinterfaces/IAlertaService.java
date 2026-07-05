package pe.edu.upc.wellnessbuddy.servicesinterfaces;

import pe.edu.upc.wellnessbuddy.entities.Alerta;

import java.util.List;

public interface IAlertaService {
    public List<Alerta> list();
    public void insert(Alerta a);
    public Alerta listId(int id);
    public void update(Alerta a);
    public void delete(int id);
    public List<Alerta> listarPorEmpleado(int idEmpleado);
}
